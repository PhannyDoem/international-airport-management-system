package com.internationalairportmanagementsystem.dtos.puts;

public record PutBaggageDto(
        Long baggageId,
        Long passengerId,
        Long flightId,
        Double weight
) {
}
