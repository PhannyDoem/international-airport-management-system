package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.UserEntity;

public record PutPassengerDto(
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        UserEntity userEntity
) {
}
