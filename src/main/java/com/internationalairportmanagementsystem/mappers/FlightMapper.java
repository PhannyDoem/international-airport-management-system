package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FlightMapper {
    public Flight postToFlight(PostFlightDto  postFlightDto) {
        Flight flight = new Flight(
                postFlightDto.flightNumber(),
                postFlightDto.departureTime(),
                postFlightDto.arrivalTime(),
                postFlightDto.arrivalAirport(),
                postFlightDto.gateAssignment()
        );
        flight.setFlightId(0L);
        return flight;
    }

    public Flight putToFlight(PutFlightDto putFlightDto) {
        Flight flight = new Flight(
                putFlightDto.flightNumber(),
                putFlightDto.departureTime(),
                putFlightDto.arrivalTime(),
                putFlightDto.arrivalAirport(),
                putFlightDto.gateAssignment()
        );
        flight.setFlightId(0L);
        return flight;
    }
}
