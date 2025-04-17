package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAircraftDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAircraftDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftService {

    Aircraft create(PostAircraftDto postAircraftDto);


    Aircraft update(Long aircraftId, PutAircraftDto putAircraftDto);

    Aircraft findById(Long aircraftId);

    List<Aircraft> findAll();

    Optional<String> deleteById(Long aircraftId);

    String deleteAll();
}
