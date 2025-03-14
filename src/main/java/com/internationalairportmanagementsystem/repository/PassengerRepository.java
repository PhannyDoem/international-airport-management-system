package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("select p from Passenger p join fetch p.userEntity u where u.userId = :userId")
    Optional<Passenger> findByUserEntityId(@Param("userId")Long userId);
}
