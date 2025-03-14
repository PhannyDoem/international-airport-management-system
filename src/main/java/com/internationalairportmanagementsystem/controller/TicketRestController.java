package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostTicketDto;
import com.internationalairportmanagementsystem.enetity.Ticket;
import com.internationalairportmanagementsystem.service.interfaces.PassengerService;
import com.internationalairportmanagementsystem.service.interfaces.TicketService;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TicketRestController {
    private TicketService ticketService;
    private UserEntityService userEntityService;
    private PassengerService  passengerService;

    @Autowired
    public TicketRestController(TicketService ticketService, UserEntityService userEntityService, PassengerService passengerService) {
        this.ticketService = ticketService;
        this.userEntityService = userEntityService;
        this.passengerService = passengerService;
    }

    @Operation(
            description = "Endpoint to add a new ticket",
            summary = "Add a new ticket",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody PostTicketDto postTicketDto){
        return ticketService.create(postTicketDto);
    }

}
