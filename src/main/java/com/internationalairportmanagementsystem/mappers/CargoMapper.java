package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Cargo;
import com.internationalairportmanagementsystem.enetity.Flight;
import org.springframework.stereotype.Service;

@Service
public class CargoMapper {
    public Cargo postToCargo(PostCargoDto postCargoDto) {
        Cargo cargo = new Cargo(
                postCargoDto.weight(),
                postCargoDto.dimensions()
        );
        cargo.setCargoId(0L);
        Flight  flight = new Flight();
        flight.setFlightId(postCargoDto.flightId());
        cargo.setFlight(flight);
        return cargo;
    }

    public Cargo putToCargo(PutCargoDto putCargoDto) {
        Cargo cargo = new Cargo(
                putCargoDto.weight(),
                putCargoDto.dimensions()
        );
        cargo.setCargoId(putCargoDto.cargoId());
        Flight   flight = new Flight();
        flight.setFlightId(putCargoDto.flightId());
        cargo.setFlight(flight);
        return cargo;
    }
}
