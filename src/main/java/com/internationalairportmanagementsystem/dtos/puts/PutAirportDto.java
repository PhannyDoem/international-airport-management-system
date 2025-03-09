package com.internationalairportmanagementsystem.dtos.puts;

public record PutAirportDto(
        Long airportId,
        String code,
        String name,
        String locationCity,
        String locationCountry
) {
}
