package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

import java.time.LocalDateTime;

public record PutCheckInDto(
        LocalDateTime checkInTime,
        Integer deskNumber,
        Passenger passenger,
        Flight flight
) {
}
