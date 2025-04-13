package com.internationalairportmanagementsystem.dtos.puts;

public record PutAirportDto(
        String code,
        String name,
        String locationCity,
        String locationCountry
) {
}
