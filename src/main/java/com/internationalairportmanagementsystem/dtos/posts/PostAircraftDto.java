package com.internationalairportmanagementsystem.dtos.posts;

public record PostAircraftDto(
        String  tailNumber,
        String model,
        Integer capacity,
        Long aircraftId
) {
}
