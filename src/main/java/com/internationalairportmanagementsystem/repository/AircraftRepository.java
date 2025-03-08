package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
}
