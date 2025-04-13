package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleAbilityDto;
import com.internationalairportmanagementsystem.enetity.Role;

public interface RoleAbilityService {
    Role create(PostRoleAbilityDto postRoleAbilityDto);
    void deleteByRoleIdAndAbilityId(Long roleId, Long abilityId);
}
