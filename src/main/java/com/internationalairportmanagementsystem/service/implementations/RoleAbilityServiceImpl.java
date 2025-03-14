package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.repository.RoleRepository;
import com.internationalairportmanagementsystem.service.interfaces.AbilityService;
import com.internationalairportmanagementsystem.service.interfaces.RoleAbilityService;
import com.internationalairportmanagementsystem.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAbilityServiceImpl implements RoleAbilityService {
    private RoleRepository roleRepository;
    private RoleService roleService;
    private AbilityService abilityService;

    @Autowired
    public RoleAbilityServiceImpl(RoleRepository roleRepository, AbilityService abilityService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.abilityService = abilityService;
    }

    @Override
    public Role create(PostRoleDto postRoleDto) {
     return roleService.create(postRoleDto);
    }

    @Override
    public String deleteByRoleIdAndAbilityId(Long roleId, Long abilityId) {
        Role role = roleService.findById(roleId);
        Ability ability = abilityService.findById(abilityId);
        role.getAbilities().remove(ability);
        roleRepository.save(role);
        return "Role Deleted Successfully";
    }
}
