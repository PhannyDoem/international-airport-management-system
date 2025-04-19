package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostMaintenanceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutMaintenanceDto;
import com.internationalairportmanagementsystem.enetity.Maintenance;
import com.internationalairportmanagementsystem.service.implementations.MaintenanceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class MaintenanceRestController {
    private final MaintenanceServiceImpl maintenanceServiceImpl;
    @Autowired
    public MaintenanceRestController(MaintenanceServiceImpl maintenanceServiceImpl) {
        this.maintenanceServiceImpl = maintenanceServiceImpl;
    }


    @Operation(
            description = "Endpoint to get all maintenances",
            summary = "Retrieve all maintenances",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all maintenances",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/maintenances")
    public ResponseEntity<List<Maintenance>> findAll() {
        return new ResponseEntity<>(maintenanceServiceImpl.findAll(), HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to get a maintenance by ID",
            summary = "Retrieve a maintenance by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Maintenance ID not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/maintenances/{maintenanceId}")
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable Long maintenanceId) {
        return new ResponseEntity<>(maintenanceServiceImpl.findById(maintenanceId), HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to add a new maintenance",
            summary = "Add a new maintenance",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/maintenances")
    public ResponseEntity<Maintenance> addMaintenance(@RequestBody PostMaintenanceDto postMaintenanceDto) {
        return new ResponseEntity<>(maintenanceServiceImpl.create(postMaintenanceDto), HttpStatus.CREATED);
    }
    @Operation(
            description = "Endpoint to update a maintenance",
            summary = "Update a maintenance",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/maintenances/{maintenanceId}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable Long maintenanceId,@RequestBody PutMaintenanceDto putMaintenanceDto) {
        return new ResponseEntity<>(maintenanceServiceImpl.update(maintenanceId, putMaintenanceDto), HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to delete a maintenance by ID",
            summary = "Delete a maintenance by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the maintenance",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Maintenance ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/maintenances/{maintenanceId}")
    public ResponseEntity<String> deleteMaintenance(@PathVariable Long maintenanceId) {
        return new ResponseEntity<>(maintenanceServiceImpl.deleteById(maintenanceId), HttpStatus.OK);
    }
}
