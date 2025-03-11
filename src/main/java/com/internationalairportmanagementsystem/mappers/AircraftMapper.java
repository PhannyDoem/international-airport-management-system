package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostAircraftDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAircraftDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Airline;
import org.springframework.stereotype.Service;

@Service
public class AircraftMapper {
    public Aircraft postToAircraft(PostAircraftDto postAircraftDto){
        Aircraft aircraft = new Aircraft(
                postAircraftDto.tailNumber(),
                postAircraftDto.model(),
                postAircraftDto.capacity()
        );
        aircraft.setAircraftId(0L);

        if (postAircraftDto.aircraftId() != null){
            Airline airline = new  Airline();
            airline.setAirlineId(postAircraftDto.aircraftId());
            aircraft.setAirline(airline);
        }
        return aircraft;
    }

    public Aircraft putToAircraft(PutAircraftDto putAircraftDto){
        Aircraft aircraft = new Aircraft(
                putAircraftDto.tailNumber(),
                putAircraftDto.model(),
                putAircraftDto.capacity()
        );
        aircraft.setAircraftId(putAircraftDto.aircraftId());

        if(putAircraftDto.airlineId() != null){
            Airline airline = new  Airline();
            airline.setAirlineId(putAircraftDto.airlineId());
            aircraft.setAirline(airline);
        }
        return aircraft;
    }
}
