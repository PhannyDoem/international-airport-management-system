package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

public record PostFeedbackDto(
        String content,
        String status,
        Passenger passenger,
        Flight flight
) {
}
