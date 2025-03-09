package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;

public record PutGateAssignmentDto(
        Long assignmentId,
        String gate,
        LocalDateTime assignmentTime,
        Long flightId
) {
}
