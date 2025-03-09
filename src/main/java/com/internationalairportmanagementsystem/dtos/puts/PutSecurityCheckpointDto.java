package com.internationalairportmanagementsystem.dtos.puts;

public record PutSecurityCheckpointDto(
        Long checkpointId,
        String location,
        String operatingHours
) {
}
