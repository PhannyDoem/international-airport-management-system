package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Airport;
import com.internationalairportmanagementsystem.enetity.GateAssignment;

import java.time.LocalDateTime;
import java.util.List;

public record PostFlightDto(
        String flightNumber,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Airport arrivalAirport,
        GateAssignment gateAssignment
) {
}
