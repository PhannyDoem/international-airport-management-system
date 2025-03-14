package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.mappers.FlightMapper;
import com.internationalairportmanagementsystem.repository.FlightRepository;
import com.internationalairportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper  flightMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper  flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public Flight create(PostFlightDto postFlightDto) {
        Flight flight = flightMapper.postToFlight(postFlightDto);
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(PutFlightDto putFlightDto) {
        Flight flight = flightMapper.putToFlight(putFlightDto);
        return flightRepository.save(flight);
    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public String deleteById(Long flightId) {
        flightRepository.deleteById(flightId);
        return "Deleted";
    }

    @Override
    public String deleteAll() {
        flightRepository.deleteAll();
        return "Deleted all successfully";
    }

    @Override
    public Boolean existsByFlightNumber(String flightNumber) {
        return flightRepository.existsByFlightNumber(flightNumber);
    }
}
