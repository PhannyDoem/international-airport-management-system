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
import java.util.Objects;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, MaintenanceMapper maintenanceMapper) {
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceMapper = maintenanceMapper;
    }

    @Override
    public Maintenance create(PostMaintenanceDto postMaintenanceDto) {
        Maintenance maintenance = maintenanceMapper.postToMaintenance(postMaintenanceDto);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance update(Long maintenanceId, PutMaintenanceDto putMaintenanceDto) {
        if (maintenanceId != null){
            maintenanceRepository.findById(maintenanceId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setAircraft(putMaintenanceDto.aircraft());
                                updated.setType(putMaintenanceDto.type());
                                updated.setDate(putMaintenanceDto.date());
                                updated.setDescription(putMaintenanceDto.description());
                                Maintenance maintenance = maintenanceRepository.save(updated);
                                return maintenanceRepository.save(maintenance);
                            }
                    );
        }
        return maintenanceRepository.findById(Objects.requireNonNull(maintenanceId))
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));
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
