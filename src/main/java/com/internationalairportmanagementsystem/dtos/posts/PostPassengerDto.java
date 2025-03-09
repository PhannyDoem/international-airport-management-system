package com.internationalairportmanagementsystem.dtos.posts;

public record PostPassengerDto(
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        String username,
        String password
) {
}
