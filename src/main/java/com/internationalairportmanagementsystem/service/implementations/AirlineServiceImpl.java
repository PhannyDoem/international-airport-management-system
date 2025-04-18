package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Airline;
import com.internationalairportmanagementsystem.mappers.AirlineMapper;
import com.internationalairportmanagementsystem.repository.AirlineRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    @Override
    public Airline create(PostAirlineDto postAirlineDto) {
        Airline airline = airlineMapper.postToAirline(postAirlineDto);
        return airlineRepository.save(airline);
    }

    @Override
    public Airline update(Long airlineId, PutAirlineDto putAirlineDto) {
        if (airlineId != null) {
            airlineRepository.findById(airlineId)
                    .stream()
                    .findFirst()
                    .ifPresent(
                            updatedAircraft -> {
                        updatedAircraft.setName(putAirlineDto.name());
                        updatedAircraft.setCode(putAirlineDto.code());
                    });
        }
        return airlineRepository.findById(Objects.requireNonNull(airlineId))
                .orElseThrow(() -> new RuntimeException("Update airline with ID not found!"));
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
