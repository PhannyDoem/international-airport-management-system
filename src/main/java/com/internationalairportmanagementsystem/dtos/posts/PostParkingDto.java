package com.internationalairportmanagementsystem.dtos.posts;

public record PostParkingDto(
        String location,
        Integer capacity,
        Double rate
) {
}
