package com.internationalairportmanagementsystem.dtos.posts;

public record PostFeedbackDto(
        String content,
        String status,
        Long passengerId,
        Long flightId
) {
}
