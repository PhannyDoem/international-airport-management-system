package com.internationalairportmanagementsystem.dtos.puts;

public record PutRentalServiceDto(
        Long rentalId,
        String type,
        String description,
        Double rate
) {
}
