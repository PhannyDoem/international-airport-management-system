package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;
import java.util.List;

public record PostFlightDto(
        String flightNumber,
        Long departureAirportId,
        Long arrivalAirportId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long aircraftId,
        List<Long> employeeIds
) {
}
