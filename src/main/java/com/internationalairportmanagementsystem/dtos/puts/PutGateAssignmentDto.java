package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;

import java.time.LocalDateTime;

public record PutGateAssignmentDto(
        String gate, LocalDateTime assignedTime, Flight flight
) {
}
