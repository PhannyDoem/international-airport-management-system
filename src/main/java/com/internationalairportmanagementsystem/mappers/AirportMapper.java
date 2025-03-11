package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportDto;
import com.internationalairportmanagementsystem.enetity.Airport;
import org.springframework.stereotype.Service;

@Service
public class AirportMapper {
    public Airport postToAirport(PostAirportDto  postAirportDto){
        Airport airport = new Airport(
                postAirportDto.name(),
                postAirportDto.code(),
                postAirportDto.locationCountry(),
                postAirportDto.locationCountry()
        );
        airport.setAirportId(0L);
        return airport;
    }

    public Airport putToAirport(PutAirportDto putAirportDto){
        Airport airport = new Airport(
                putAirportDto.code(),
                putAirportDto.name(),
                putAirportDto.locationCountry(),
                putAirportDto.locationCountry()
        );
        airport.setAirportId(putAirportDto.airportId());

        return airport;
    }


}
