package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Ticket;

import java.time.LocalDateTime;

public record PutBoardingPassDto(
        String gate,
        LocalDateTime boardingTime,
        Ticket ticket
) {
}
