package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;

public record PutCheckInDto(
        Long checkInId,
        Long passengerId,
        Long flightId,
        LocalDateTime checkInTime,
        Integer deskNumber
) {
}
