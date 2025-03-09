package com.internationalairportmanagementsystem.dtos.posts;

public record PostAirportDto(
        String code,
        String name,
        String locationCity,
        String locationCountry
) {
}
