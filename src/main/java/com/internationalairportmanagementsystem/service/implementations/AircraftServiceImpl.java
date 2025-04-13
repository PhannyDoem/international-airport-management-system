package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAircraftDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAircraftDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.mappers.AircraftMapper;
import com.internationalairportmanagementsystem.repository.AircraftRepository;
import com.internationalairportmanagementsystem.service.interfaces.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository  aircraftRepository;
    private final AircraftMapper  aircraftMapper;

    @Autowired
    public AircraftServiceImpl(AircraftRepository aircraftRepository, AircraftMapper aircraftMapper) {
        this.aircraftRepository = aircraftRepository;
        this.aircraftMapper = aircraftMapper;
    }
    @Override
    public Aircraft create(PostAircraftDto postAircraftDto) {
        Aircraft aircraft = aircraftMapper.postToAircraft(postAircraftDto);
      return   aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft update(Long aircraftId, PutAircraftDto putAircraftDto) {
        if (aircraftId != null) {
            aircraftRepository.findById(aircraftId).stream().findFirst().map(
                    updatedAircraft -> {
                       updatedAircraft.setTailNumber(putAircraftDto.tailNumber());
                       updatedAircraft.setModel(putAircraftDto.model());
                       updatedAircraft.setCapacity(putAircraftDto.capacity());
                       updatedAircraft.setAirline(putAircraftDto.airline());
                       Aircraft aircraft = aircraftRepository.save(updatedAircraft);
                       return aircraftRepository.save(aircraft);
                    }
            );
        }
        return aircraftRepository.findById(Objects.requireNonNull(aircraftId))
                .orElseThrow(()-> new RuntimeException("Aircraft not found"));
    }

    @Override
    public Aircraft findById(Long aircraftId) {
        Optional<Aircraft> result = aircraftRepository.findById(aircraftId);
        Aircraft aircraft = null;
        if(result.isPresent()) {
            aircraft = result.get();
        }else {
            throw new RuntimeException("Aircraft with Id  " + aircraftId + " not found");
        }
        return aircraft;
    }

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }

    @Override
    public String deleteById(Long aircraftId) {
        aircraftRepository.deleteById(aircraftId);
        return "Aircraft deleted successfully";
    }

    @Override
    public String deleteAll() {
       aircraftRepository.deleteAll();
       return "Aircraft deleted successfully";
    }
}
