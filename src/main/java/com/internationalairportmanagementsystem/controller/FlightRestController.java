package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.service.implementations.FlightServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightRestController {

    private final FlightServiceImpl flightServiceImpl;

    @Autowired
    public FlightRestController(FlightServiceImpl flightServiceImpl) {
        this.flightServiceImpl = flightServiceImpl;
    }

    @Operation(
            description = "Endpoint to get all flights",
            summary = "Retrieve all flights",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all flights",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/flights")
    public ResponseEntity<List<Flight>> findAll() {
        return  new ResponseEntity<>(flightServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a flight by ID",
            summary = "Retrieve a flight by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Flight ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/flights/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        return new ResponseEntity<>(flightServiceImpl.findById(flightId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new flight",
            summary = "Add a new flight",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/flights")
    public ResponseEntity<Flight> addFlight(@RequestBody PostFlightDto postFlightDto) {
        return new ResponseEntity<>(flightServiceImpl.create(postFlightDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Endpoint to update a flight",
            summary = "Update a flight",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/flights/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long flightId, @RequestBody PutFlightDto putFlightDto) {
        return new ResponseEntity<>(flightServiceImpl.update(flightId, putFlightDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a flight by ID",
            summary = "Delete a flight by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the flight",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Flight ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/flights/{flightId}")
    public ResponseEntity<String> deleteFlightById(@PathVariable Long flightId) {
        return new ResponseEntity<>(flightServiceImpl.deleteById(flightId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete all flights",
            summary = "Delete all flights",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all flights",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/flights")
    public ResponseEntity<String> deleteAllFlights() {
      return new ResponseEntity<>(flightServiceImpl.deleteAll(), HttpStatus.OK);
    }
}