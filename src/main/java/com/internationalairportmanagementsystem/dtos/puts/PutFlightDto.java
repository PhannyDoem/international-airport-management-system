package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;
import java.util.List;

public record PutFlightDto(
        Long flightId,
        String flightNumber,
        Long departureAirportId,
        Long arrivalAirportId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long aircraftId,
        List<Integer> employeeIds
) {
}
