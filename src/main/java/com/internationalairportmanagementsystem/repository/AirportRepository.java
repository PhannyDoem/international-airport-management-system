package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
}
