package com.internationalairportmanagementsystem.dtos.posts;

import java.util.List;

public record PostRoleDto(
        String roleName,
        List<Long> abilityIds
) {
}
