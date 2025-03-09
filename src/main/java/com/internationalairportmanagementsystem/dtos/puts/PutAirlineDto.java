package com.internationalairportmanagementsystem.dtos.puts;

public record PutAirlineDto(
        Long airlineId,
        String code,
        String name
) {
}
