package com.internationalairportmanagementsystem.dtos.puts;

public record PutUserDto(
        Long userId,
        String username,
        String password,
        Long roleId
) {
}
