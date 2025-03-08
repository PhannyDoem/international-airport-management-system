package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
}
