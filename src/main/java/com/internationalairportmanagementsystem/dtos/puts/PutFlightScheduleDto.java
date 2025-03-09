package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;

public record PutFlightScheduleDto(
        Long scheduleId,
        Long flightId,
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status
) {
}
