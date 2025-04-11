package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Aircraft;

import java.time.LocalDateTime;

public record PostMaintenanceDto(
        String description,
        LocalDateTime date,
        String type,
        Aircraft aircraft
) {
}
