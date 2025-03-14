package com.internationalairportmanagementsystem.security;

import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.repository.AbilityRepository;
import com.internationalairportmanagementsystem.repository.EmployeeRepository;
import com.internationalairportmanagementsystem.repository.PassengerRepository;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private UserEntityRepository  userRepository;
    private AbilityRepository abilityRepository;
    private PassengerRepository passengerRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public CustomAuthorizationManager(
            UserEntityRepository userRepository,
            AbilityRepository abilityRepository,
            PassengerRepository passengerRepository,
            EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.abilityRepository = abilityRepository;
        this.passengerRepository = passengerRepository;
        this.employeeRepository = employeeRepository;
    }

    public CustomAuthorizationManager(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public AuthorizationDecision check(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext requestAuthorizationContext) {

        Authentication auth = authentication.get();
        if(!auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken)
            return new AuthorizationDecision(false);


        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username not found"));

        List<Ability> userAbilities = abilityRepository.findByRoleId(user.getRole().getRoleId());
        user.getRole().setAbilities(userAbilities);

        HttpServletRequest request = requestAuthorizationContext.getRequest();
        return new  AuthorizationDecision(isAuthorized(user, request));
    }

    private boolean isAuthorized(UserEntity user, HttpServletRequest request) {
        List<Ability> abilities = user.getRole().getAbilities();

        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        for (Ability ability : abilities) {
            if (requestURI.contains(ability.getEntity()) &&
                    (ability.getVerb() == null ||
                            ability.getVerb().equals(method))) {
                return false;
            }
        }
        return true;
    }
}
