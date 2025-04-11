package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Aircraft;

import java.time.LocalDateTime;

public record PutMaintenanceDto(
        String description,
        LocalDateTime date,
        String type,
        Aircraft aircraft
) {
}
