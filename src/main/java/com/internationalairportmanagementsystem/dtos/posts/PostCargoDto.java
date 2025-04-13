package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.Flight;

public record PostCargoDto(
        Double weight,
        String dimension,
        Flight flight
) {
}
