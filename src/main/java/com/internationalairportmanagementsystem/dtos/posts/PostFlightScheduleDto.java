package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;

import java.time.LocalDateTime;

public record PostFlightScheduleDto(
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status,
        Flight flight
) {
}
