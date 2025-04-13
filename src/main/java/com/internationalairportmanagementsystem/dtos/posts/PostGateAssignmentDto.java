package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;

import java.time.LocalDateTime;

public record PostGateAssignmentDto(
        String gate,
        LocalDateTime assignedTime,
        Flight flight
) {
}
