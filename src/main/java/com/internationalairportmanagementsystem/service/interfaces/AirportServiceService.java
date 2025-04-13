package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportServiceDto;

import java.util.List;

public interface AirportServiceService {

    AirportService create(PostAirportServiceDto  postAirportServiceDto);

    AirportService update(Long airportServiceId, PutAirportServiceDto putAirportServiceDto);

    AirportService findById(Long serviceId);

    List<AirportService> findAll();

    String  deleteById(Long serviceId);

    String  deleteAll();

}
