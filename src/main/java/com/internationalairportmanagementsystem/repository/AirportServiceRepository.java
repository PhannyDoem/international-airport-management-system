package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.AirportService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportServiceRepository extends JpaRepository<AirportService,Long> {
}
