package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;

import java.util.List;

public interface AirlineService {

    // this is a method for post or create
    Airline create(PostAirlineDto postAirlineDto);

    // update
    Airline update(PutAirlineDto  putAirlineDto);

    List<Airline>  findAll();

    Airline findById(Long airlineId);

    String deleteById(Long airlineId);

    String deleteAll();
}
