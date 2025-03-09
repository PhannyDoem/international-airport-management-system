package com.internationalairportmanagementsystem.dtos.puts;

public record PutTicketDto(
        Long ticketId,
        Long flightId,
        Long passengerId,
        String seatNumber,
        String _class,
        Double price
) {
}
