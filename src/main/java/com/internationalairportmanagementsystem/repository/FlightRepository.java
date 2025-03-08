package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Boolean existsByFlightNumber(String flightNumber);
}
