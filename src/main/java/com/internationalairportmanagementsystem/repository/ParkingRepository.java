package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
