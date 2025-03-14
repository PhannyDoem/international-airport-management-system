package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostMaintenanceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutMaintenanceDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Maintenance;

public class MaintenanceMapper {
    public Maintenance postToMaintenance(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                postMaintenanceDto.date(),
                postMaintenanceDto.type(),
                postMaintenanceDto.description()
        );
        maintenance.setMaintenanceId(0L);
        if (postMaintenanceDto.aircraftId() != null) {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(postMaintenanceDto.aircraftId());
            maintenance.setAircraft(aircraft);
        }
        return maintenance;
    }

    public Maintenance putToMaintenance(PutMaintenanceDto putMaintenanceDto) {
        Maintenance maintenance = new Maintenance(
                putMaintenanceDto.date(),
                putMaintenanceDto.type(),
                putMaintenanceDto.description()
        );
        maintenance.setMaintenanceId(putMaintenanceDto.maintenanceId());
        if (putMaintenanceDto.aircraftId() != null) {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(putMaintenanceDto.aircraftId());
            maintenance.setAircraft(aircraft);
        }
        return maintenance;
    }
}
