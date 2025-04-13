package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostMaintenanceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutMaintenanceDto;
import com.internationalairportmanagementsystem.enetity.Maintenance;
import com.internationalairportmanagementsystem.mappers.MaintenanceMapper;
import com.internationalairportmanagementsystem.repository.MaintenanceRepository;
import com.internationalairportmanagementsystem.service.interfaces.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private MaintenanceRepository maintenanceRepository;
    private MaintenanceMapper maintenanceMapper;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance create(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.postToMaintenance(postMaintenanceDto);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance update(Long maintenanceId, PutMaintenanceDto putMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.putToMaintenance(putMaintenanceDto);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance findById(Long maintenanceId) {
        return maintenanceRepository.findById(maintenanceId).orElse(null);
    }

    @Override
    public String deleteById(Long maintenanceId) {
        maintenanceRepository.deleteById(maintenanceId);
        return "Delete Maintenance Successfully";
    }
}
