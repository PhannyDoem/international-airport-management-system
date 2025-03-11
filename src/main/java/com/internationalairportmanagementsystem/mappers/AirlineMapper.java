package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;
import org.springframework.stereotype.Service;

@Service
public class AirlineMapper {
    public Airline postToAirline(PostAirlineDto postAirlineDto){
        Airline airline = new Airline(
                postAirlineDto.code(),
                postAirlineDto.name()
        );
        airline.setAirlineId(0L);
        return airline;
    }

    public Airline putToAirline(PutAirlineDto putAirlineDto){
        Airline airline = new Airline(
                putAirlineDto.code(),
                putAirlineDto.name()
        );
        airline.setAirlineId(0L);
        return airline;
    }
}
