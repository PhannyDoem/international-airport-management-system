package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Airport;
import com.internationalairportmanagementsystem.mappers.AirportMapper;
import com.internationalairportmanagementsystem.repository.AirportRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

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
    public Airport update(Long airportId, PutAirportDto putAirportDto) {
        if (airportId !=null){
            airportRepository.findById(airportId)
                    .stream()
                    .findFirst()
                    .map(
                            updatedAirport -> {
                                updatedAirport.setCode(putAirportDto.code());
                                updatedAirport.setName(putAirportDto.name());
                                updatedAirport.setLocationCity(putAirportDto.locationCity());
                                updatedAirport.setLocationCountry(putAirportDto.locationCountry());
                                Airport aircraft = airportRepository.save(updatedAirport);
                                return airportRepository.save(aircraft);
                            }
                    );
        }
        return airportRepository.findById(Objects.requireNonNull(airportId))
                .orElseThrow(()-> new RuntimeException("Update Airport with Id not found"));
    }

    @Override
    public Airport findById(Long airportId) {
        return airportRepository.findById(airportId)
                .orElseThrow(()-> new RuntimeException("Airport with Id not found"));
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
