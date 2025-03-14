package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportDto;
import com.internationalairportmanagementsystem.enetity.Airport;
import com.internationalairportmanagementsystem.mappers.AirportMapper;
import com.internationalairportmanagementsystem.repository.AirportRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    private AirportRepository airportRepository;
    private AirportMapper airportMapper;

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }
    @Override
    public Airport create(PostAirportDto postAirportDto) {
        Airport airport = airportMapper.postToAirport(postAirportDto);
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(PutAirportDto putAirportDto) {
        Airport airport = airportMapper.putToAirport(putAirportDto);
        return airportRepository.save(airport);
    }

    @Override
    public Airport findById(Long airportId) {
        Optional<Airport> result = airportRepository.findById(airportId);
        Airport airport = null;
        if (result.isPresent()) {
            airport = result.get();
        }else {
            throw new RuntimeException("Airport with Id  " + airportId + " not found");
        }
        return airport;
    }

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Override
    public String deleteById(Long airportId) {
        airportRepository.deleteById(airportId);
        return "Airport with Id  " + airportId + " deleted successfully";
    }

    @Override
    public String deleteAll() {
        airportRepository.deleteAll();
        return "Airport deleted successfully";
    }
}
