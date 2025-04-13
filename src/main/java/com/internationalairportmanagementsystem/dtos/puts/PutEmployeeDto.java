package com.internationalairportmanagementsystem.dtos.puts;

import com.internationalairportmanagementsystem.enetity.UserEntity;

public record PutEmployeeDto(
        String name,
        String role,
        String contactInfo,
        UserEntity userEntity
) {
}
