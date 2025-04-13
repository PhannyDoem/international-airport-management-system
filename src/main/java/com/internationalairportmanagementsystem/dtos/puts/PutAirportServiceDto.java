package com.internationalairportmanagementsystem.dtos.puts;

public record PutAirportServiceDto(
        String name,
        String location,
        String operatingHours
) {
}
