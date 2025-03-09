package com.internationalairportmanagementsystem.dtos.posts;

import java.time.LocalDateTime;

public record PostEmployeeDto(
        String name,
        String role,
        String contactInfo,
        String username,
        String password
) {
}
