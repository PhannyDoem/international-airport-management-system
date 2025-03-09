package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostCheckInDto(
        Long passengerId,
        Long flightId,
        LocalDateTime checkInTime,
        Integer deskNumber
) {
}
