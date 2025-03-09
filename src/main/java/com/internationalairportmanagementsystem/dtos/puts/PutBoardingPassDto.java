package com.internationalairportmanagementsystem.dtos.puts;

import java.time.LocalDateTime;

public record PutBoardingPassDto(
        Long boardingPassId,
        String gate,
        LocalDateTime boardingTime,
        Long ticketId
) {
}
