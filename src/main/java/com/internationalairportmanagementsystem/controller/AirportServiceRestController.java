package com.internationalairportmanagementsystem.controller;


import com.internationalairportmanagementsystem.dtos.posts.PostAirportServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAirportServiceDto;
import com.internationalairportmanagementsystem.service.implementations.AirportServiceServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.AirportService;
import com.internationalairportmanagementsystem.service.interfaces.AirportServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportServiceRestController {

    private final AirportServiceServiceImpl airportServiceServiceImpl;

    @Autowired
    public AirportServiceRestController(AirportServiceServiceImpl theAirportServiceServiceImpl){
        airportServiceServiceImpl=theAirportServiceServiceImpl;
    }

    @Operation(
            description = "Get endpoint to retrieve all airport services. This endpoint returns a list of all services available at the airport.",
            summary = "Retrieve all airport services",
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
    @GetMapping("/public/airport_services")
    public ResponseEntity<List<AirportService>> findAllAirportServices(){
        return new ResponseEntity<>(airportServiceServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve an airport service by its ID. This is useful for fetching the details of a specific airport service.",
            summary = "Retrieve an airport service by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/airport_services/{airportServiceId}")
    public ResponseEntity<AirportService> getAirportServiceById(@PathVariable Long airportServiceId){
        return new ResponseEntity<>(airportServiceServiceImpl.findById(airportServiceId), HttpStatus.OK);
    }

    @Operation(
            description = "Post endpoint to add a new airport service. This allows for the creation of a new service at the airport.",
            summary = "Add a new airport service",
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
    @PostMapping("/private/airport_services")
    public ResponseEntity<AirportService> addAirportService(@RequestBody PostAirportServiceDto postAirportServiceDto){
        return new ResponseEntity<>(airportServiceServiceImpl.create(postAirportServiceDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing airport service. This can be used to modify the details of an airport service.",
            summary = "Update an airport service",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/airports/service/{serviceId}")
    public ResponseEntity<AirportService> updateAirportService(@PathVariable Long serviceId,@RequestBody PutAirportServiceDto putAirportServiceDto){
        return new ResponseEntity<>(airportServiceServiceImpl.update(serviceId,putAirportServiceDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove an airport service by its ID. This is useful for deleting a specific service at the airport that is no longer needed.",
            summary = "Delete an airport service by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Airport service not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/airport/services/{airportServiceId}")
    public ResponseEntity<String> deleteAirportServiceById(@PathVariable Long airportServiceId){
        return new ResponseEntity<>(airportServiceServiceImpl.deleteById(airportServiceId), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove all airport services. This is useful for bulk deletion of all services at the airport.",
            summary = "Delete all airport services",
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
    @DeleteMapping("/private/airport_services")
    public ResponseEntity<String> deleteAllAirportServices() {
       return new ResponseEntity<>(airportServiceServiceImpl.deleteAll(), HttpStatus.OK);
    }
}
