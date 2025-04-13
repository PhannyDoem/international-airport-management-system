package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Airline;

public record PutAircraftDto(
        String tailNumber,
        String model,
        Integer capacity,
        Airline airline
) {
}
