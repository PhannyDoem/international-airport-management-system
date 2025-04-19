package com.internationalairportmanagementsystem.controller;


import com.internationalairportmanagementsystem.dtos.posts.PostGateAssignmentDto;
import com.internationalairportmanagementsystem.dtos.puts.PutGateAssignmentDto;
import com.internationalairportmanagementsystem.enetity.GateAssignment;
import com.internationalairportmanagementsystem.service.implementations.GateAssignmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GateAssignmentRestController {

    private final GateAssignmentServiceImpl gateAssignmentServiceImpl;

    @Autowired
    public GateAssignmentRestController(GateAssignmentServiceImpl gateAssignmentServiceImpl) {
        this.gateAssignmentServiceImpl = gateAssignmentServiceImpl;
    }

    @Operation(
            description = "Endpoint to get all gate assignments",
            summary = "Retrieve all gate assignments",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all gate assignments",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/public/gate_assignments")
    public ResponseEntity<List<GateAssignment>> findAllGateAssignments() {
        return new ResponseEntity<>(gateAssignmentServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to get a gate assignment by ID",
            summary = "Retrieve a gate assignment by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Gate Assignment ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/public/gate_assignments/{gateAssignmentId}")
    public ResponseEntity<GateAssignment> getGateAssignmentById(@PathVariable Long gateAssignmentId) {
        return new ResponseEntity<>(gateAssignmentServiceImpl.findById(gateAssignmentId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to add a new gate assignment",
            summary = "Add a new gate assignment",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/private/gate_assignments")
    public ResponseEntity<GateAssignment> addGateAssignment(@RequestBody PostGateAssignmentDto postGateAssignmentDto) {
        return new ResponseEntity<>(gateAssignmentServiceImpl.create(postGateAssignmentDto), HttpStatus.CREATED);
    }


    @Operation(
            description = "Endpoint to update a gate assignment",
            summary = "Update a gate assignment",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/gate_assignments/{assignmentId}")
    public ResponseEntity<GateAssignment> updateGateAssignment(@PathVariable Long assignmentId,@RequestBody PutGateAssignmentDto putGateAssignmentDto) {
        return new ResponseEntity<>(gateAssignmentServiceImpl.update(assignmentId,putGateAssignmentDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a gate assignment by ID",
            summary = "Delete a gate assignment by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the gate assignment",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Gate Assignment ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/gate_assignments/{gateAssignmentId}")
    public ResponseEntity<String> deleteGateAssignmentById(@PathVariable Long gateAssignmentId) {
        return new ResponseEntity<>(gateAssignmentServiceImpl.deleteById(gateAssignmentId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete all gate assignments",
            summary = "Delete all gate assignments",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all gate assignments",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/gate_assignments")
    public ResponseEntity<String> deleteAllGateAssignments() {
        return new ResponseEntity<>(gateAssignmentServiceImpl.deleteAll(), HttpStatus.OK);
    }
}
