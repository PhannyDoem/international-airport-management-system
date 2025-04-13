package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Ticket;

import java.time.LocalDateTime;

public record PostBoardingPassDto(
        String gate,
        LocalDateTime boardingTime,
        Ticket ticket
) {
}
