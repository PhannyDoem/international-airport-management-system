package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

public record PutBaggageDto(
        Double weight,
        Flight flight,
        Passenger passenger
) {
}
