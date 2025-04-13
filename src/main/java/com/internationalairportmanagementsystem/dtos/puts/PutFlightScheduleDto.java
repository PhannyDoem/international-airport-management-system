package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;

import java.time.LocalDateTime;

public record PutFlightScheduleDto(
        LocalDateTime scheduledDepartureTime,
        LocalDateTime scheduledArrivalTime,
        String status,
        Flight flight
) {
}
