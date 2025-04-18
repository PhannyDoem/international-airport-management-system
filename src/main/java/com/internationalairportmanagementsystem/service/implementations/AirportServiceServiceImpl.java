package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportServiceDto;
import com.internationalairportmanagementsystem.mappers.AirportServiceMapper;
import com.internationalairportmanagementsystem.repository.AirportServiceRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirportService;
import com.internationalairportmanagementsystem.service.interfaces.AirportServiceService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AirportServiceServiceImpl implements AirportServiceService{
    private final AirportServiceRepository airportServiceRepository;
    private final AirportServiceMapper airportServiceMapper;

    public AirportServiceServiceImpl(AirportServiceRepository airportServiceRepository, AirportServiceMapper airportServiceMapper) {
        this.airportServiceRepository = airportServiceRepository;
        this.airportServiceMapper = airportServiceMapper;
    }


    @Override
    public AirportService create(PostAirportServiceDto postAirportServiceDto) {
        com.internationalairportmanagementsystem.enetity.AirportService airportService = airportServiceMapper.postToAirportService(postAirportServiceDto);
        return (AirportService) airportServiceRepository.save(airportService);
    }

    @Override
    public AirportService update(Long airportServiceId, PutAirportServiceDto putAirportServiceDto) {
        if (airportServiceId != null){
            airportServiceRepository.findById(airportServiceId)
                    .stream()
                    .findFirst()
                    .map(
                            updated ->{
                                updated.setName(putAirportServiceDto.name());
                                updated.setLocation(putAirportServiceDto.location());
                                updated.setOperatingHours(putAirportServiceDto.operatingHours());
                                com.internationalairportmanagementsystem.enetity.AirportService airportService = airportServiceRepository.save(updated);
                                return airportServiceRepository.save(airportService);
                            });
        }
        return (AirportService) airportServiceRepository.findById(Objects.requireNonNull(airportServiceId))
                .orElseThrow(() -> new RuntimeException("Update with Id Not found!"));
    }

    @Override
    public AirportService findById(Long serviceId) {
        return (AirportService) airportServiceRepository.findById(serviceId).orElseThrow(()-> new RuntimeException("Not found"));
    }

    @Override
    public List<AirportService> findAll() {
        return Collections.singletonList((AirportService) airportServiceRepository.findAll());
    }

    @Override
    public String deleteById(Long serviceId) {
        airportServiceRepository.deleteById(serviceId);
        return "Deleted AirportService with id " + serviceId;
    }

    @Override
    public String deleteAll() {
        airportServiceRepository.deleteAll();
        return "Deleted All AirportService";
    }
}
