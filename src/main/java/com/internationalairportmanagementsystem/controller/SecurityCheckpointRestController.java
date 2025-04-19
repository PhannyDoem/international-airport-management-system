package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostSecurityCheckpointDto;
import com.internationalairportmanagementsystem.dtos.puts.PutSecurityCheckpointDto;
import com.internationalairportmanagementsystem.enetity.SecurityCheckPoint;
import com.internationalairportmanagementsystem.service.implementations.SecurityCheckpointServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class SecurityCheckpointRestController {

    private final SecurityCheckpointServiceImpl securityCheckpointServiceImpl;

    @Autowired
    public SecurityCheckpointRestController(SecurityCheckpointServiceImpl securityCheckpointServiceImpl) {
        this.securityCheckpointServiceImpl = securityCheckpointServiceImpl;
    }




    @Operation(
            description = "Get all security checkpoints",
            summary = "Get all security checkpoints",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all security checkpoints",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/security_checkpoints")
    public ResponseEntity<List<SecurityCheckPoint>> findAllSecurityCheckpoints(){
        return new ResponseEntity<>(securityCheckpointServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get a security checkpoint by ID",
            summary = "Get a security checkpoint by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Security checkpoint ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/security_checkpoints/{securityCheckpointId}")
    public ResponseEntity<SecurityCheckPoint> getSecurityCheckpointById(@PathVariable Long securityCheckpointId){
        return new ResponseEntity<>(securityCheckpointServiceImpl.findById(securityCheckpointId), HttpStatus.OK);
    }

    @Operation(
            description = "Add a new security checkpoint",
            summary = "Add a new security checkpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/security_checkpoints")
    public ResponseEntity<SecurityCheckPoint> addSecurityCheckpoint(@RequestBody PostSecurityCheckpointDto postSecurityCheckpointDto){
        return new ResponseEntity<>(securityCheckpointServiceImpl.create(postSecurityCheckpointDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Update a security checkpoint",
            summary = "Update a security checkpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/security_checkpoints/{securityId}")
    public ResponseEntity<SecurityCheckPoint> updateSecurityCheckpoint(@PathVariable Long securityId,@RequestBody PutSecurityCheckpointDto putSecurityCheckpointDto){
        return new ResponseEntity<>(securityCheckpointServiceImpl.update(securityId,putSecurityCheckpointDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete a security checkpoint by ID",
            summary = "Delete a security checkpoint by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the security checkpoint",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Security checkpoint ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/security_checkpoints/{securityCheckpointId}")
    public ResponseEntity<String> deleteSecurityCheckpointById(@PathVariable Long securityCheckpointId){
        return new ResponseEntity<>(securityCheckpointServiceImpl.deleteById(securityCheckpointId), HttpStatus.OK);
    }
    @Operation(
            description = "Delete all security checkpoints",
            summary = "Delete all security checkpoints",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all security checkpoints",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/security_checkpoints")
    public ResponseEntity<String> deleteAllSecurityCheckpoints() {
        return new ResponseEntity<>(securityCheckpointServiceImpl.deleteAll(), HttpStatus.OK);
    }
}