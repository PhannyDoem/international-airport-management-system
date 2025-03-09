package com.internationalairportmanagementsystem.dtos.posts;

public record PostCargoDto(
        Long flightId,
        Double weight,
        String dimensions
) {
}
