package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BaggageRepository extends JpaRepository<Baggage,Long> {

    @Query("SELECT b FROM Baggage b JOIN FETCH b.passenger p WHERE p.passengerId = :passengerId")
    List<Baggage> findByPassengerId(@Param("passengerId") Long passengerId);
}
