package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostAircraftDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAircraftDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.service.implementations.AircraftServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class AircraftRestController {
    private final AircraftServiceImpl aircraftServiceImpl;

    @Autowired
    public AircraftRestController(AircraftServiceImpl aircraftServiceImpl) {
        this.aircraftServiceImpl = aircraftServiceImpl;
    }

    @Operation(
            description = "Post endpoint to add a new aircraft. This allows for the creation of a new aircraft in the system.",
            summary = "Add a new aircraft",
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
    @PostMapping("/aircraft")
    public ResponseEntity<Aircraft> create(@RequestBody PostAircraftDto postAircraftDto) {
        return new ResponseEntity<>(aircraftServiceImpl.create(postAircraftDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing aircraft. This can be used to modify the details of an aircraft, such as its model or status.",
            summary = "Update an aircraft",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Aircraft not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/aircraft/{aircraftId}")
    public ResponseEntity<Aircraft> update(@PathVariable Long aircraftId ,@RequestBody PutAircraftDto putAircraftDto) {
        return new ResponseEntity<>(aircraftServiceImpl.update(aircraftId, putAircraftDto), HttpStatus.OK);
    }
    @Operation(
            description = "Get endpoint to retrieve all aircraft. This endpoint returns a list of all aircraft registered in the system.",
            summary = "Retrieve all aircraft",
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

    @GetMapping("/aircrafts")
    public ResponseEntity<List<Aircraft>> getAllAircrafts() {
        return new ResponseEntity<>(aircraftServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve an aircraft by its ID. This is useful for fetching the details of a specific aircraft.",
            summary = "Retrieve an aircraft by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Aircraft not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/aircraft/{aircraftId}")
    public ResponseEntity<String> deleteAircraftById(@PathVariable Long aircraftId) {
        Aircraft aircraft = aircraftServiceImpl.findById(aircraftId);
        if (aircraft == null) {
            throw new RuntimeException("Delete aircraft field");
        }
        return  new ResponseEntity<>(aircraftServiceImpl.deleteById(aircraftId), HttpStatus.OK);
    }
    @Operation(
            description = "Post endpoint to add a new aircraft. This allows for the creation of a new aircraft in the system.",
            summary = "Add a new aircraft",
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
    @DeleteMapping("aircraft/delete-all")
    public String deleteAll(){
        aircraftServiceImpl.deleteAll();
        return "Deleted all aircrafts";
    }
}
