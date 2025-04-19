package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostTicketDto;
import com.internationalairportmanagementsystem.dtos.puts.PutTicketDto;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.Ticket;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairportmanagementsystem.service.implementations.TicketServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/private")
public class TicketRestController {
    private  final TicketServiceImpl ticketServiceImpl;
    private final UserEntityService userEntityService;

    @Autowired
    public TicketRestController(TicketServiceImpl theTicketServiceImpl,
                                UserEntityService userEntityService
    ){
        this.ticketServiceImpl = theTicketServiceImpl;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Endpoint to get all tickets",
            summary = "Get all tickets",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all tickets",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/tickets")
    public List<Ticket> findAll(){
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return ticketServiceImpl.findByPassengerId(passenger.getPassengerId());
        }

        return ticketServiceImpl.findAll();
    }

    @Operation(
            description = "Endpoint to get a ticket by ID",
            summary = "Get a ticket by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ticket ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId){

        Ticket theTicket = ticketServiceImpl.findById(ticketId);
        if (theTicket == null) {
            throw new RuntimeException("Ticket id not found - " + ticketId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theTicket);

        return new ResponseEntity<>(theTicket,  HttpStatus.OK) ;
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
    public ResponseEntity<Ticket> addTicket(@RequestBody PostTicketDto postTicketDto){
        return new ResponseEntity<>(ticketServiceImpl.create(postTicketDto), HttpStatus.CREATED) ;

    }

    @Operation(
            description = "Endpoint to update a ticket",
            summary = "Update a ticket",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId,@RequestBody PutTicketDto putTicketDto){
        return new ResponseEntity<>(ticketServiceImpl.update(ticketId, putTicketDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a ticket by ID",
            summary = "Delete a ticket by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the ticket",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ticket ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId){
        return new ResponseEntity<>(ticketServiceImpl.deleteById(ticketId), HttpStatus.OK);
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, Ticket ticket) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (!Objects.equals(passenger.getPassengerId(), ticket.getPassenger().getPassengerId())) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}