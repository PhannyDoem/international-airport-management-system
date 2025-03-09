package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostGateAssignmentDto(
        String gate,
        LocalDateTime assignmentTime,
        Long flightId
) {
}
