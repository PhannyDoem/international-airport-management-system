package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostFlightScheduleDto(
        Long flightId,
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status
) {
}
