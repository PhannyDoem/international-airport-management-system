package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostParkingDto;
import com.internationalairportmanagementsystem.dtos.puts.PutParkingDto;
import com.internationalairportmanagementsystem.enetity.Parking;
import com.internationalairportmanagementsystem.service.implementations.ParkingServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ParkingRestController {

    private final ParkingServiceImpl parkingServiceImpl;

    public ParkingRestController(ParkingServiceImpl parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
    }


    @Operation(
            description = "Endpoint to get all parkings",
            summary = "Retrieve all parkings",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all parkings",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/parkings")
    public ResponseEntity<List<Parking>> findAllParkings(){
        return new ResponseEntity<>(parkingServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a parking by ID",
            summary = "Retrieve a parking by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Parking ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/parkings/{parkingId}")
    public ResponseEntity<Parking> getParkingById(@PathVariable Long parkingId){
        return new ResponseEntity<>(parkingServiceImpl.findById(parkingId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new parking",
            summary = "Add a new parking",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/parkings")
    public ResponseEntity<Parking> addParking(@RequestBody PostParkingDto postParkingDto){
        return new ResponseEntity<>(parkingServiceImpl.create(postParkingDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Endpoint to update a parking",
            summary = "Update a parking",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/parkings/{parkingId}")
    public ResponseEntity<Parking> updateParking(@PathVariable Long parkingId,@RequestBody PutParkingDto putParkingDto){
        return new ResponseEntity<>(parkingServiceImpl.update(parkingId,putParkingDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a parking by ID",
            summary = "Delete a parking by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the parking",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Parking ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/parkings/{parkingId}")
    public ResponseEntity<String> deleteParkingById(@PathVariable Long parkingId){
       return new ResponseEntity<>(parkingServiceImpl.deleteById(parkingId), HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to delete all parkings",
            summary = "Delete all parkings",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all parkings",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/parkings")
    public ResponseEntity<String> deleteAllParkings() {
       return new ResponseEntity<>(parkingServiceImpl.deleteAllParking(), HttpStatus.OK);
    }
}
