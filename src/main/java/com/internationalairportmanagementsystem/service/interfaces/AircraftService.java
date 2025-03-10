package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAircraftDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAircraftDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;

import java.util.List;

public interface AircraftService {

    Aircraft create(PostAircraftDto postAircraftDto);


    Aircraft update(PutAircraftDto putAircraftDto);

    Aircraft findById(Long aircraftId);

    List<Aircraft> findAll();

    String deleteById(Long aircraftId);

    String deleteAll();
}
