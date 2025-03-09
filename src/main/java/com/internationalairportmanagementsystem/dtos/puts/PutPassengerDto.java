package com.internationalairportmanagementsystem.dtos.puts;

public record PutPassengerDto(
        Long passengerId,
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        String username,
        String password
) {
}
