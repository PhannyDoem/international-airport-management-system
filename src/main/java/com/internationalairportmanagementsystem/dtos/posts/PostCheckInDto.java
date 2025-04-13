package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

import java.time.LocalDateTime;

public record PostCheckInDto(
        LocalDateTime checkInTime,
        Integer deskNumber,
        Passenger passenger,
        Flight flight
) {
}
