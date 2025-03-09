package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostMaintenanceDto(
        LocalDateTime date,
        String type,
        String description,
        Long aircraftId
) {
}
