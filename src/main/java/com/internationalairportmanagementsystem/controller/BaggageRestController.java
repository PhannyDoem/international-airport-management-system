package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostBaggageDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBaggageDto;
import com.internationalairportmanagementsystem.enetity.Baggage;
import com.internationalairportmanagementsystem.service.implementations.BaggageServiceImpl;
import com.internationalairportmanagementsystem.service.implementations.PassengerServiceImpl;
import com.internationalairportmanagementsystem.service.implementations.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class BaggageRestController {

    private final BaggageServiceImpl baggageServiceImpl;

    @Autowired
    public BaggageRestController(BaggageServiceImpl baggageServiceImpl
                                 ) {
        this.baggageServiceImpl = baggageServiceImpl;
    }


    @Operation(
            description = "Get endpoint to retrieve all baggage records. If the user is a passenger, it returns only their baggage records.",
            summary = "Retrieve all baggage records",
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
    @GetMapping("/baggages")
    public ResponseEntity<List<Baggage>> findAll() {
        return new ResponseEntity<>(baggageServiceImpl.findAll(), HttpStatus.OK);
    }


    @Operation(
            description = "Get endpoint to retrieve a baggage record by its ID. Only the owner of the baggage (if a passenger) or an authorized user can access it.",
            summary = "Retrieve a baggage record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/baggages/{baggageId}")
    public ResponseEntity<Baggage> getBaggage(@PathVariable Long baggageId) {
        return new ResponseEntity<>(baggageServiceImpl.findById(baggageId), HttpStatus.OK);
    }


    @Operation(
            description = "Post endpoint to add a new baggage record. This allows for the creation of a new baggage record.",
            summary = "Add a new baggage record",
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
    @PostMapping("/baggages")
    public ResponseEntity<Baggage> addBaggage(@RequestBody PostBaggageDto postBaggageDto) {
        return new ResponseEntity<>(baggageServiceImpl.create(postBaggageDto), HttpStatus.OK);
    }

    @Operation(
            description = "Put endpoint to update an existing baggage record. Only the owner of the baggage (if a passenger) or an authorized user can update it.",
            summary = "Update a baggage record",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/baggages/{baggageId}")
    public ResponseEntity<Baggage> updateBaggage(@PathVariable Long baggageId, @RequestBody PutBaggageDto putBaggageDto) {
        return new ResponseEntity<>(baggageServiceImpl.update(baggageId, putBaggageDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove a baggage record by its ID. Only the owner of the baggage (if a passenger) or an authorized user can delete it.",
            summary = "Delete a baggage record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Baggage not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/baggages/{baggageId}")
    public ResponseEntity<String> deleteBaggage(@PathVariable Long baggageId) {
        return new ResponseEntity<>(baggageServiceImpl.deleteById(baggageId), HttpStatus.OK);
    }
}
