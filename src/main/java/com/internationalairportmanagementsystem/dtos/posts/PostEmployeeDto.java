package com.internationalairportmanagementsystem.dtos.posts;

import com.internationalairportmanagementsystem.enetity.UserEntity;


public record PostEmployeeDto(
        String name,
        String role,
        String contactInfo,
        UserEntity userEntity
) {
}
