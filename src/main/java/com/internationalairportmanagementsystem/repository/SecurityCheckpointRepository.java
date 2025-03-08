package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.SecurityCheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityCheckpointRepository extends JpaRepository<SecurityCheckPoint, Long> {
}
