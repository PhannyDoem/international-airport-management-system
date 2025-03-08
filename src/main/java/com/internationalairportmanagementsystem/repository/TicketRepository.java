package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    @Query("select  t  from Ticket t join fetch t.passenger p where p.passengerId = :passengerId")
    List<Ticket> findByPassengerId(@Param("passengerId") Long passengerId);
}
