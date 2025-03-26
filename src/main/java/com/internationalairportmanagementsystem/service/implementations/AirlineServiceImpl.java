package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;
import com.internationalairportmanagementsystem.mappers.AirlineMapper;
import com.internationalairportmanagementsystem.repository.AirlineRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {
    private AirlineRepository airlineRepository;
    private AirlineMapper airlineMapper;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    @Override
    public Airline create(PostAirlineDto postAirlineDto) {
        return airlineMapper.postToAirline(postAirlineDto);
    }

    @Override
    public Airline update(PutAirlineDto putAirlineDto) {
        return airlineMapper.putToAirline(putAirlineDto);
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findById(Long airlineId) {
        return airlineRepository.findById(airlineId).orElse(null);
    }

    @Override
    public String deleteById(Long airlineId) {
        airlineRepository.deleteById(airlineId);
        return "Deleted Airline";
    }

    @Override
    public String deleteAll() {
        airlineRepository.deleteAll();
        return "Deleted All Airlines";
    }
}
