package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;
import com.internationalairportmanagementsystem.service.interfaces.AirlineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirlineRestController {

    private AirlineService airlineService;

    @Autowired
    public AirlineRestController(AirlineService airlineService) {
        this.airlineService = airlineService;
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
    public List<Airline> findAll() {
        return airlineService.findAll();
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
    @GetMapping("/public/airlines/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.findById(id);
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
    public Airline addAirline(@RequestBody PostAirlineDto postAirlineDto) {
        return airlineService.create(postAirlineDto);
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
    public Airline updateAirline(@RequestBody PutAirlineDto putAirlineDto) {
        return airlineService.update(putAirlineDto);
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
    @DeleteMapping("/private/airlines/{id}")
    public String deleteAirlineById(@PathVariable Long id) {
        airlineService.deleteById(id);
        return "Deleted airline with id - " + id;
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
    public String deleteAllAirlines() {
        return airlineService.deleteAll();
    }
}