package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.repository.RoleRepository;
import com.internationalairportmanagementsystem.service.interfaces.AbilityService;
import com.internationalairportmanagementsystem.service.interfaces.RoleAbilityService;
import com.internationalairportmanagementsystem.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleAbilityServiceImpl implements RoleAbilityService {
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final AbilityService abilityService;

    @Autowired
    public RoleAbilityServiceImpl(RoleRepository roleRepository, AbilityService abilityService, RoleService roleService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.abilityService = abilityService;
    }

    @Override
    public Role create(PostRoleAbilityDto postRoleAbilityDto) {
     Role role = roleService.findById(postRoleAbilityDto.roleId());
     Optional<Ability> ability = abilityService.findById(postRoleAbilityDto.abilityId());
     return roleRepository.save(role);
    }

    @Override
    public void deleteByRoleIdAndAbilityId(Long roleId, Long abilityId) {
        Role role = roleService.findById(roleId);
        Optional<Ability> ability = abilityService.findById(abilityId);
        role.getAbilities().remove(ability);
        roleRepository.save(role);
    }
}
