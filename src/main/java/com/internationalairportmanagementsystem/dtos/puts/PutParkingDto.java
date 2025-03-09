package com.internationalairportmanagementsystem.dtos.puts;

public record PutParkingDto(
        Long parkingId,
        String location,
        Integer capacity,
        Double rate
) {
}
