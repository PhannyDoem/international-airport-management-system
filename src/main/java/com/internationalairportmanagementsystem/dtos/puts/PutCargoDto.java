package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.Flight;

public record PutCargoDto(
        Double weight,
        String dimension,
        Flight flight
) {
}
