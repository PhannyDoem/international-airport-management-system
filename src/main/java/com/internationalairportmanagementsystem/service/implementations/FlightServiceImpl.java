package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.mappers.FlightMapper;
import com.internationalairportmanagementsystem.repository.FlightRepository;
import com.internationalairportmanagementsystem.service.interfaces.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl( FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public Flight create(PostFlightDto postFlightDto) {
        Flight flight = flightMapper.postToFlight(postFlightDto);
        return flightRepository.save(flight);
    }

    @Override
    public Flight update(Long flightId, PutFlightDto putFlightDto) {
        if (flightId != null) {
            flightRepository.findById(flightId)
                    .stream()
                    .findFirst()
                    .map(
                            updatedFlight -> {
                                updatedFlight.setArrivalAirport(putFlightDto.arrivalAirport());
                                updatedFlight.setArrivalTime(putFlightDto.arrivalTime());
                                updatedFlight.setArrivalAirport(putFlightDto.arrivalAirport());
                                updatedFlight.setArrivalTime(putFlightDto.arrivalTime());
                                Flight flight = flightRepository.save(updatedFlight);
                                return flightRepository.save(flight);

                            }
                    );
        }
        return flightRepository.findById(Objects.requireNonNull(flightId))
                .orElseThrow(()-> new RuntimeException("Update flight with ID not found"));

    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(()-> new RuntimeException("Find flight with ID not found"));
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public String deleteById(Long flightId) {
        flightRepository.deleteById(flightId);
        return "Delete flight with ID " + flightId;
    }

    @Override
    public String deleteAll() {
        flightRepository.deleteAll();
        return "Delete all flights";
    }

    @Override
    public Boolean existsByFlightNumber(String flightNumber) {
        return flightRepository.existsByFlightNumber(flightNumber);
    }
}