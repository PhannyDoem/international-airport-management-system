package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostAirportDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportDto;
import com.internationalairportmanagementsystem.enetity.Airport;
import com.internationalairportmanagementsystem.service.implementations.AirportServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Airport")
public class AirportRestController {
    private final AirportServiceImpl airportServiceImpl;

    @Autowired
    public AirportRestController(AirportServiceImpl airportServiceImpl) {
        this.airportServiceImpl = airportServiceImpl;
    }

    @Operation(
            description = "Get endpoint for retrieving all airports",
            summary = "This is an endpoint used to get all the airports",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Error",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airports")
    public ResponseEntity<List<Airport>> findAll() {
        return new ResponseEntity<>(airportServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve an airport by its ID",
            summary = "This endpoint is used to retrieve an airport by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airports/{airportId}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        return new ResponseEntity<>(airportServiceImpl.findById(airportId), HttpStatus.OK);
    }


    @Operation(
            description = "Post endpoint to add a new airport",
            summary = "This endpoint is used to add a new airport",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/airports")
    public ResponseEntity<Airport> addAirport(@RequestBody PostAirportDto postAirportDto) {
        return new ResponseEntity<>(airportServiceImpl.create(postAirportDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing airport",
            summary = "This enpoint is used to update an airport",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Invalid input",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/airports/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody PutAirportDto puttAirportDto) {
        return new ResponseEntity<>(airportServiceImpl.update(airportId, puttAirportDto), HttpStatus.OK);
    }


    @Operation(
            description = "Delete endpoint to remove an airport by its ID",
            summary = "This endpoint is used to delete an airport by its ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airports/{airportId}")
    public ResponseEntity<String> deleteAirportById(@PathVariable Long airportId) {
        return new ResponseEntity<>(airportServiceImpl.deleteById(airportId), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove all airports",
            summary = "This endpoint is used to delete all airports",
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
    @DeleteMapping("/private/airports")
    public ResponseEntity<String> deleteAllAirports() {
        return new ResponseEntity<>(airportServiceImpl.deleteAll(), HttpStatus.OK);
    }
}
