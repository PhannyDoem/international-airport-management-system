package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.BoardingPass;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

import java.math.BigDecimal;

public record PutTicketDto(
        String seatNumber,
        String _class,
        BigDecimal price,
        BoardingPass boardingPass,
        Flight flight,
        Passenger passenger
) {
}
