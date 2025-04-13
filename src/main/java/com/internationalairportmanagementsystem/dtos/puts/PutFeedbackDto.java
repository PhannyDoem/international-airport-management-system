package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

public record PutFeedbackDto(
        String content,
        String status,
        Passenger passenger,
        Flight flight
) {
}
