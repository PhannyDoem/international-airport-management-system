package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightScheduleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightScheduleDto;
import com.internationalairportmanagementsystem.enetity.FlightSchedule;
import com.internationalairportmanagementsystem.service.implementations.FlightScheduleServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.FlightScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightScheduleRestController {

    private final FlightScheduleServiceImpl flightScheduleServiceImpl;

    @Autowired
    public FlightScheduleRestController(FlightScheduleServiceImpl flightScheduleServiceImpl) {
        this.flightScheduleServiceImpl = flightScheduleServiceImpl;
    }

    @Operation(
            description = "Endpoint to get all flight schedules",
            summary = "Retrieve all flight schedules",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all flight schedules",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/flight_schedules")
    public ResponseEntity<List<FlightSchedule>> findAllFlightSchedules() {
        return new ResponseEntity<>(flightScheduleServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a flight schedule by ID",
            summary = "Retrieve a flight schedule by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Flight Schedule ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/flight_schedules/{flightScheduleId}")
    public ResponseEntity<FlightSchedule> getFlightScheduleById(@PathVariable Long flightScheduleId) {
        return new ResponseEntity<>(flightScheduleServiceImpl.findById(flightScheduleId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new flight schedule",
            summary = "Add a new flight schedule",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/flight_schedules")
    public ResponseEntity<FlightSchedule> addFlightSchedule(@RequestBody PostFlightScheduleDto postFlightScheduleDto) {
        return new ResponseEntity<>(flightScheduleServiceImpl.create(postFlightScheduleDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Endpoint to update a flight schedule",
            summary = "Update a flight schedule",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/flight_schedules/{scheduleId}")
    public ResponseEntity<FlightSchedule> updateFlightSchedule(@PathVariable Long scheduleId,@RequestBody PutFlightScheduleDto putFlightScheduleDto) {
        return new ResponseEntity<>(flightScheduleServiceImpl.update(scheduleId, putFlightScheduleDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a flight schedule by ID",
            summary = "Delete a flight schedule by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the flight schedule",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Flight Schedule ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/flight_schedules/{flightScheduleId}")
    public  ResponseEntity<String> deleteFlightScheduleById(@PathVariable Long flightScheduleId) {
        return new ResponseEntity<>(flightScheduleServiceImpl.deleteById(flightScheduleId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete all flight schedules",
            summary = "Delete all flight schedules",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all flight schedules",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/flight_schedules")
    public ResponseEntity<String> deleteAllFlightSchedules() {
        return new ResponseEntity<>(flightScheduleServiceImpl.deleteAll(), HttpStatus.OK);
    }
}