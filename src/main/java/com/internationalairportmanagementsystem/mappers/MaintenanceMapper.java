package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostMaintenanceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutMaintenanceDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Maintenance;

import java.time.LocalDateTime;

public class MaintenanceMapper {
    public Maintenance postToMaintenance(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                postMaintenanceDto.description(),
                postMaintenanceDto.date(),
                postMaintenanceDto.type(),
                postMaintenanceDto.aircraft()
        );
        maintenance.setMaintenanceId(0L);
        return maintenance;
    }

    public Maintenance putToMaintenance(PutMaintenanceDto putMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                putMaintenanceDto.description(),
                putMaintenanceDto.date(),
                putMaintenanceDto.type(),
                putMaintenanceDto.aircraft()
        );
        maintenance.setMaintenanceId(0L);
        return maintenance;
    }
}
