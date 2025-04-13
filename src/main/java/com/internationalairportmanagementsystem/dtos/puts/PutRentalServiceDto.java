package com.internationalairportmanagementsystem.dtos.puts;

public record PutRentalServiceDto(
        String type,
        String description,
        Double rate
) {
}
