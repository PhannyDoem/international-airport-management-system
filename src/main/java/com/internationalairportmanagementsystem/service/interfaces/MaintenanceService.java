package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostMaintenanceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutMaintenanceDto;
import com.internationalairportmanagementsystem.enetity.Maintenance;

import java.util.List;

public interface MaintenanceService {
    Maintenance create(PostMaintenanceDto postMaintenanceDto);
    Maintenance update(Long maintenanceId, PutMaintenanceDto putMaintenanceDto);

    List<Maintenance> findAll();
    Maintenance findById(Long maintenanceId);

    String deleteById(Long maintenanceId);
}
