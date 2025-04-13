package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Airline;

public record PostAircraftDto(
        String tailNumber,
        String model,
        Integer capacity,
        Airline airline
) {
}
