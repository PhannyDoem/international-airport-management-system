package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    @Query("SELECT c from CheckIn c join fetch c.passenger p where p.passengerId = :passengerId")
    List<CheckIn> findByPassengerId(@Param("passengerId")Long passengerId);
}
