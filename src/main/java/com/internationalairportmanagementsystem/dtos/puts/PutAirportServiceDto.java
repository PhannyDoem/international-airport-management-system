package com.internationalairportmanagementsystem.dtos.puts;

public record PutAirportServiceDto(
        Long serviceId,
        String name,
        String location,
        String operatingHours
) {
}
