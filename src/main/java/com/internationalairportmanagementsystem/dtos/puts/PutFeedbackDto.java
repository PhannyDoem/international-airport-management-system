package com.internationalairportmanagementsystem.dtos.puts;

public record PutFeedbackDto(
        Long feedbackId,
        String content,
        String status,
        Long passengerId,
        Long flightId
) {
}
