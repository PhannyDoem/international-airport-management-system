package com.internationalairportmanagementsystem.dtos.puts;

import java.util.List;

public record PutRoleDto(
        Long roleId,
        String roleName,

        List<Long> abilityIds
) {
}
