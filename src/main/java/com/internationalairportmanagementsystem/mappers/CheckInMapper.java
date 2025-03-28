package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostCheckInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCheckInDto;
import com.internationalairportmanagementsystem.enetity.CheckIn;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;
import org.springframework.stereotype.Service;

@Service
public class CheckInMapper {
    public CheckIn postToCheckIn(PostCheckInDto postCheckInDto) {
        CheckIn checkIn = new CheckIn(
                postCheckInDto.checkInTime(),
                postCheckInDto.deskNumber()
                );
        checkIn.setCheckInId(0L);

        if(postCheckInDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(postCheckInDto.passengerId());
            checkIn.setPassenger(passenger);
        }
        if (postCheckInDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(postCheckInDto.flightId());
            checkIn.setFlight(flight);
        }
        return checkIn;
    }

    public CheckIn putToCheckIn(PutCheckInDto putCheckInDto) {
        CheckIn checkIn = new CheckIn(
                putCheckInDto.checkInTime(),
                putCheckInDto.deskNumber()
        );
        checkIn.setCheckInId(putCheckInDto.checkInId());

        if(putCheckInDto.passengerId() != null){
            Passenger passenger = new Passenger();
            passenger.setPassengerId(putCheckInDto.passengerId());
            checkIn.setPassenger(passenger);
        }
        if (putCheckInDto.flightId() != null){
            Flight flight = new Flight();
            flight.setFlightId(putCheckInDto.flightId());
            checkIn.setFlight(flight);
        }
        return checkIn;
    }
}
