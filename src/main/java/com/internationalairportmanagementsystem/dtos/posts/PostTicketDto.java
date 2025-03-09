package com.internationalairportmanagementsystem.dtos.posts;

import java.math.BigDecimal;

public record PostTicketDto(
        Long flightId,
        Long passengerId,
        String seatNumber,
        String _class,
        BigDecimal price
) {
}
