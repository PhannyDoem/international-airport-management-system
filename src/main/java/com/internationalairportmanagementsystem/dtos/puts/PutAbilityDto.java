package com.internationalairportmanagementsystem.dtos.puts;

public record PutAbilityDto(
        Long abilityId,
        String entity,
        String verb,
        String field
) {
}
