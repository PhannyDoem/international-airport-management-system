package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;

public record PutMaintenanceDto(
        Long maintenanceId,
        LocalDateTime date,
        String type,
        String description,
        Long aircraftId
) {
}
