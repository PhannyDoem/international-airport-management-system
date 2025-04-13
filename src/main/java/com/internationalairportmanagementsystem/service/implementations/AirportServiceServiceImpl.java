package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportServiceDto;
import com.internationalairportmanagementsystem.mappers.AirportServiceMapper;
import com.internationalairportmanagementsystem.repository.AirportServiceRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirportService;
import com.internationalairportmanagementsystem.service.interfaces.AirportServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceServiceImpl implements AirportServiceService {
    private AirportServiceRepository airportServiceRepository;
    private AirportServiceMapper airportServiceMapper;
    @Autowired
    public AirportServiceServiceImpl(AirportServiceRepository airportServiceRepository,  AirportServiceMapper airportServiceMapper) {
        this.airportServiceRepository = airportServiceRepository;
        this.airportServiceMapper = airportServiceMapper;
    }
    @Override
    public AirportService create(PostAirportServiceDto postAirportServiceDto) {
        return null;
    }

    @Override
    public AirportService update(Long airportServiceId, PutAirportServiceDto putAirportServiceDto) {
        return null;
    }

    @Override
    public AirportService findById(Long serviceId) {
        return null;
    }

    @Override
    public List<AirportService> findAll() {
        return List.of();
    }

    @Override
    public String deleteById(Long serviceId) {
        return "";
    }

    @Override
    public String deleteAll() {
        return "";
    }
}
