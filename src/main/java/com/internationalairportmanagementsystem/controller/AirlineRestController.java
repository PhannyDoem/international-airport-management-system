package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;
import com.internationalairportmanagementsystem.service.implementations.AirlineServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirlineRestController {

    private final AirlineServiceImpl airlineServiceImpl;

    @Autowired
    public AirlineRestController(AirlineServiceImpl airlineServiceImpl) {
        this.airlineServiceImpl = airlineServiceImpl;
    }

    @Operation(
            description = "Get endpoint to retrieve all airlines. This endpoint returns a list of all airlines registered in the system.",
            summary = "Retrieve all airlines",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airlines")
    public ResponseEntity<List<Airline>> findAll() {
        return new ResponseEntity<>(airlineServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve an airline by its ID. This is useful for fetching the details of a specific airline.",
            summary = "Retrieve an airline by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airline not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airlines/{airlineId}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long airlineId) {
        return new ResponseEntity<>(airlineServiceImpl.findById(airlineId), HttpStatus.OK);
    }

    @Operation(
            description = "Post endpoint to add a new airline. This allows for the creation of a new airline in the system.",
            summary = "Add a new airline",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/airlines")
    public ResponseEntity<Airline> addAirline(@RequestBody PostAirlineDto postAirlineDto) {
        return new ResponseEntity<>(airlineServiceImpl.create(postAirlineDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing airline. This can be used to modify the details of an airline, such as its name or status.",
            summary = "Update an airline",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airline not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/airlines")
    public ResponseEntity<Airline> updateAirline(@PathVariable long airlineId, @RequestBody PutAirlineDto putAirlineDto) {
        return new ResponseEntity<>(airlineServiceImpl.update(airlineId, putAirlineDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove an airline by its ID. This is useful for deleting a specific airline that is no longer needed.",
            summary = "Delete an airline by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airlines/{airlineId}")
    public ResponseEntity<String> deleteAirlineById(@PathVariable Long airlineId) {
        return new ResponseEntity<>(airlineServiceImpl.deleteById(airlineId), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove all airlines. This is useful for bulk deletion of all airlines in the system.",
            summary = "Delete all airlines",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airlines")
    public ResponseEntity<String> deleteAllAirlines() {
        return new ResponseEntity<>(airlineServiceImpl.deleteAll(), HttpStatus.OK);
    }
}