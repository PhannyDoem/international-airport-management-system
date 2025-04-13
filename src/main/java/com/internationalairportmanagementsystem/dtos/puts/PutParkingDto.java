package com.internationalairportmanagementsystem.dtos.puts;

public record PutParkingDto(
        String location,
        Integer capacity,
        Double rate
) {
}
