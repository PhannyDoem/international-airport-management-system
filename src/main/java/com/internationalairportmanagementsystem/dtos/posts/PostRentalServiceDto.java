package com.internationalairportmanagementsystem.dtos.posts;

public record PostRentalServiceDto(
        String type,
        String description,
        Double rate
) {
}
