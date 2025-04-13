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
                postAircraftDto.capacity(),
                postAircraftDto.airline()
        );
        aircraft.setAircraftId(0L);
        return aircraft;
    }

    public Aircraft putToAircraft(PutAircraftDto putAircraftDto){
        Aircraft aircraft = new Aircraft(
                putAircraftDto.tailNumber(),
                putAircraftDto.model(),
                putAircraftDto.capacity(),
                putAircraftDto.airline()
        );
        aircraft.setAircraftId(0L);
        return aircraft;
    }
}
