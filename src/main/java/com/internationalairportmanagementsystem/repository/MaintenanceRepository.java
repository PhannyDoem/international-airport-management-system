package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}
