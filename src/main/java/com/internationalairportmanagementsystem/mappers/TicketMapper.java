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
                postTicketDto.price()
        );
        ticket.setTicketId(0L);

        Passenger passenger = new Passenger();
        Flight flight = new Flight();
        passenger.setPassengerId(postTicketDto.passengerId());
        flight.setFlightId(postTicketDto.flightId());

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        return ticket;
    }
    public Ticket putToTicket(PutTicketDto putTicketDto) {
        Ticket ticket = new Ticket(
                putTicketDto.seatNumber(),
                putTicketDto._class(),
                putTicketDto.price()
        );
        ticket.setTicketId(putTicketDto.ticketId());

        Passenger passenger = new Passenger();
        Flight flight = new Flight();
        passenger.setPassengerId(putTicketDto.passengerId());
        flight.setFlightId(putTicketDto.flightId());

        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        return ticket;
    }
}
