package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostTicketDto;
import com.internationalairportmanagementsystem.dtos.puts.PutTicketDto;
import com.internationalairportmanagementsystem.enetity.Ticket;
import com.internationalairportmanagementsystem.mappers.TicketMapper;
import com.internationalairportmanagementsystem.repository.TicketRepository;
import com.internationalairportmanagementsystem.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public Ticket create(PostTicketDto postTicketDto) {
        Ticket  ticket = ticketMapper.postToTicket(postTicketDto);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long ticketId, PutTicketDto putTicketDto) {
        if (ticketId != null){
            ticketRepository.findById(ticketId)
                    .stream()
                    .findFirst()
                    .map(
                            update -> {
                                update.setFlight(putTicketDto.flight());
                                update.setPassenger(putTicketDto.passenger());
                                update.setPrice(putTicketDto.price());
                                update.setBoardingPass(putTicketDto.boardingPass());
                                update.set_class(putTicketDto._class());
                                update.setSeatNumber(putTicketDto.seatNumber());
                                Ticket ticket = ticketRepository.save(update);
                                return ticketRepository.save(ticket);
                            }
                    );
        }
        return ticketRepository.findById(Objects.requireNonNull(ticketId))
                .orElseThrow(()-> new  RuntimeException("Update not found"));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public List<Ticket> findByPassengerId(Long passengerId) {
        return ticketRepository.findByPassengerId(passengerId);
    }

    @Override
    public String deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
        return "Deleted ticket";
    }
}
