package com.internationalairportmanagementsystem.dtos.posts;

public record PostBaggageDto(
        Long passengerId,
        Long flightId,
        Double weight
) {
}
