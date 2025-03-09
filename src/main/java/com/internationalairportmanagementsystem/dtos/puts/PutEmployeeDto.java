package com.internationalairportmanagementsystem.dtos.puts;

public record PutEmployeeDto(
        Long employeeId,
        String name,
        String role,
        String contactInfo,
        String username,
        String password
) {
}
