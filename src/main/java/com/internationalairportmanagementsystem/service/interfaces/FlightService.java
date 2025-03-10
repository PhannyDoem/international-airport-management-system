package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.Flight;

import java.util.List;

public interface FlightService {
    Flight create(PostFlightDto   postFlightDto);
    Flight update(PutFlightDto putFlightDto);
    Flight  findById(Long flightId);

    List<Flight> findAll();

    String deleteById(Long flightId);

    String deleteAll();

    Boolean existsByFlightNumber(String flightNumber);
}
