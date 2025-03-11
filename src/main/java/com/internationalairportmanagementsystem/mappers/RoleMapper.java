package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.enetity.Role;

public class RoleMapper {
    public Role postRole(PostRoleDto  postRoleDto) {
        Role role = new Role(postRoleDto.roleName());
        role.setRoleId(0L);

        if(postRoleDto.abilityIds() != null && !postRoleDto.abilityIds().isEmpty()) {
            for(Long abilityId : postRoleDto.abilityIds()){
                Ability ability = new Ability();
                ability.setAbilityId(abilityId);
                role.getAbilities().add(ability);
            }
        }
        return role;
    }

    public Role putToRole(PutRoleDto putRoleDto) {
        Role role = new Role(putRoleDto.roleName());
        role.setRoleId(putRoleDto.roleId());
        if(putRoleDto.abilityIds() != null && !putRoleDto.abilityIds().isEmpty()) {
            for(Long abilityId : putRoleDto.abilityIds()){
                Ability ability = new Ability();
                ability.setAbilityId(abilityId);
                role.getAbilities().add(ability);
            }
        }
        return role;
    }
}
