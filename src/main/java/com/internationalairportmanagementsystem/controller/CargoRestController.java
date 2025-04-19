package com.internationalairportmanagementsystem.controller;


import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Cargo;
import com.internationalairportmanagementsystem.service.implementations.CargoServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class CargoRestController {

    private final CargoServiceImpl cargoServiceImpl;

    @Autowired
    public CargoRestController(CargoServiceImpl cargoServiceImpl) {
        this.cargoServiceImpl = cargoServiceImpl;
    }




    @Operation(
            description = "Get endpoint to retrieve all cargo records",
            summary = "Retrieve all cargo records",
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
    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> findAll(){
        return new ResponseEntity<>(cargoServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve a cargo record by its ID",
            summary = "Retrieve a cargo record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/cargos/{cargoId}")
    public ResponseEntity<Cargo> getCargo(@PathVariable Long cargoId){
        return new ResponseEntity<>(cargoServiceImpl.findById(cargoId), HttpStatus.OK);
    }

    @Operation(
            description = "Post endpoint to add a new cargo record",
            summary = "Add a new cargo record",
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
    @PostMapping("/cargos")
    public ResponseEntity<Cargo> addCargo(@RequestBody PostCargoDto postCargoDto){
        return new ResponseEntity<>(cargoServiceImpl.create(postCargoDto), HttpStatus.CREATED);
    }


    @Operation(
            description = "Put endpoint to update an existing cargo record",
            summary = "Update a cargo record",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/cargos/{cargoId}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable Long cargoId,@RequestBody PutCargoDto putCargoDto){
        return new ResponseEntity<>(cargoServiceImpl.update(cargoId, putCargoDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove a cargo record by its ID",
            summary = "Delete a cargo record by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Cargo not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/cargos/{cargoId}")
    public ResponseEntity<String> deleteCargoById(@PathVariable Long cargoId) {
        return new ResponseEntity<>(cargoServiceImpl.deleteById(cargoId), HttpStatus.OK);
    }
}
