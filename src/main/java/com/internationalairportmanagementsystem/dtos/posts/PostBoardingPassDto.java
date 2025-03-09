package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostBoardingPassDto(
        String gate,
        LocalDateTime boardingTime,
        Long ticketId
) {
}
