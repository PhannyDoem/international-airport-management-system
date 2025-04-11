package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;

public record PostBaggageDto(
        Double weight,
        Flight flight,
        Passenger passenger
) {
}
