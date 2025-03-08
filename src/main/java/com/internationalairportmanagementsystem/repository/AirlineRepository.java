package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,Long> {
}
