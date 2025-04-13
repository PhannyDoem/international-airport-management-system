package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.UserEntity;

public record PostPassengerDto(
        String name,
        String passportNumber,
        String nationality,
        String contactDetails,
        UserEntity userEntity
) {
}
