package com.internationalairportmanagementsystem.controller;
import com.internationalairportmanagementsystem.dtos.posts.PostRentalServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRentalServiceDto;
import com.internationalairportmanagementsystem.enetity.RentalService;
import com.internationalairportmanagementsystem.service.implementations.RentalServiceServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.RentalServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalServiceRestController {

    private final RentalServiceServiceImpl rentalServiceServiceImpl;

    @Autowired
    public RentalServiceRestController(RentalServiceServiceImpl rentalServiceServiceImpl){
        this.rentalServiceServiceImpl=rentalServiceServiceImpl;
    }

    @Operation(
            description = "Endpoint to get all rental services",
            summary = "Retrieve all rental services",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all rental services",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/rental_services")
    public ResponseEntity<List<RentalService>> findAllRentalServices(){
        return new ResponseEntity<>(rentalServiceServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a rental service by ID",
            summary = "Retrieve a rental service by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Rental service ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/rental_services/{rentalServiceId}")
    public ResponseEntity<RentalService> getRentalServiceById(@PathVariable Long rentalServiceId){
        return new ResponseEntity<>(rentalServiceServiceImpl.findById(rentalServiceId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new rental service",
            summary = "Add a new rental service",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/rental_services")
    public ResponseEntity<RentalService> addRentalService(@RequestBody PostRentalServiceDto postRentalServiceDto){
        return new ResponseEntity<>(rentalServiceServiceImpl.create(postRentalServiceDto),  HttpStatus.CREATED);
    }


    @Operation(
            description = "Endpoint to update a rental service",
            summary = "Update a rental service",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/rental_services/{rentalServiceId}")
    public ResponseEntity<RentalService> updateRentalService(@PathVariable Long rentalServiceId,@RequestBody PutRentalServiceDto putRentalServiceDto){
        return new ResponseEntity<>(rentalServiceServiceImpl.update(rentalServiceId,putRentalServiceDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a rental service by ID",
            summary = "Delete a rental service by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the rental service",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Rental service ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/rental_services/{rentalServiceId}")
    public ResponseEntity<String> deleteRentalServiceById(@PathVariable Long rentalServiceId){
        return new  ResponseEntity<>(rentalServiceServiceImpl.deleteById(rentalServiceId), HttpStatus.OK);
    }

}