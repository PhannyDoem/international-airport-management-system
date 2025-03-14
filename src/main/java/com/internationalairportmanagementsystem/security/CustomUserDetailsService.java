package com.internationalairportmanagementsystem.security;

import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserEntityRepository  userRepository;

    @Autowired
    public  CustomUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private List<SimpleGrantedAuthority> mapRolesToAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getRoleName()));
    }
}
