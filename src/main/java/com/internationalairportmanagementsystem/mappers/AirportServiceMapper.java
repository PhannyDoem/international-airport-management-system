package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportServiceDto;
import com.internationalairportmanagementsystem.enetity.AirportService;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceMapper {
    public AirportService postToAirportService(PostAirportServiceDto postAirportServiceDto) {
        AirportService airportService = new AirportService(
                postAirportServiceDto.name(),
                postAirportServiceDto.location(),
                postAirportServiceDto.operatingHours()
        );
        airportService.setServiceId(0L);
        return airportService;
    }
    public AirportService putAirportService(PutAirportServiceDto putAirportServiceDto) {
        AirportService airportService = new AirportService(
                putAirportServiceDto.name(),
                putAirportServiceDto.location(),
                putAirportServiceDto.operatingHours()
        );
        airportService.setServiceId(0L);
        return airportService;
    }

}
