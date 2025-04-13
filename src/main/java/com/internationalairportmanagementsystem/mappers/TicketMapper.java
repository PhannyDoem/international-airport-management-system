package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostTicketDto;
import com.internationalairportmanagementsystem.dtos.puts.PutTicketDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {
    public Ticket postToTicket(PostTicketDto postTicketDto) {
        Ticket ticket = new Ticket(
                postTicketDto.seatNumber(),
                postTicketDto._class(),
                postTicketDto.price(),
                postTicketDto.boardingPass(),
                postTicketDto.flight(),
                postTicketDto.passenger()
        );
        ticket.setTicketId(0L);
        return ticket;
    }
    public Ticket putToTicket(PutTicketDto putTicketDto) {
        Ticket ticket = new Ticket(
                putTicketDto.seatNumber(),
                putTicketDto._class(),
                putTicketDto.price(),
                putTicketDto.boardingPass(),
                putTicketDto.flight(),
                putTicketDto.passenger()
        );
        ticket.setTicketId(0L);
        return ticket;
    }
}
