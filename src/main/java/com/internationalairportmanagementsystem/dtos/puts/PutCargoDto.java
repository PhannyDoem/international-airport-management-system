package com.internationalairportmanagementsystem.dtos.puts;

public record PutCargoDto(
        Long cargoId,
        Long flightId,
        Double weight,
        String dimensions
) {
}
