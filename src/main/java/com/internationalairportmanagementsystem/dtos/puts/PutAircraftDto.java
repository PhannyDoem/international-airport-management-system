package com.internationalairportmanagementsystem.dtos.puts;

public record PutAircraftDto(
        Long aircraftId,
        String tailNumber,
        String model,
        Integer capacity,
        Long airlineId
) {
}
