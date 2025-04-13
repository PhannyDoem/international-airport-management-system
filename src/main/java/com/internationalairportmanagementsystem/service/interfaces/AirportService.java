package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportDto;
import com.internationalairportmanagementsystem.enetity.Airport;

import java.util.List;

public interface AirportService {


    Airport create(PostAirportDto postAirportDto);

    Airport update(Long airportId, PutAirportDto putAirportDto);

    Airport findById(Long airportId);

    List<Airport> findAll();

    String  deleteById(Long airportId);

    String deleteAll();

}
