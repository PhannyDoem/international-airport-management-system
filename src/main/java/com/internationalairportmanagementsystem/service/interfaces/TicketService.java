package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostTicketDto;
import com.internationalairportmanagementsystem.dtos.puts.PutTicketDto;
import com.internationalairportmanagementsystem.enetity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket create(PostTicketDto  postTicketDto);
    Ticket update(PutTicketDto putTicketDto);
    List<Ticket> findAll();
    Ticket findById(Long ticketId);
    List<Ticket> findByPassengerId(Long passengerId);
    String deleteById(Long ticketId);
}
