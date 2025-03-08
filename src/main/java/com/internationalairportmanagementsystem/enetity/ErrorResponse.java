package com.internationalairportmanagementsystem.enetity;



public class ErrorResponse {
    private int status;
    private String message;
    private Long timestamp;

    public ErrorResponse() {}

    public ErrorResponse(int status, String message, Long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
