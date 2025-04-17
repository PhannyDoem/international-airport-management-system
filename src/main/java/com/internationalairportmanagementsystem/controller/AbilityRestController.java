package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostAbilityDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.service.implementations.AbilityServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.AbilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/private")
public class AbilityRestController {
    private final AbilityServiceImpl abilityServiceImpl;
    @Autowired
    public AbilityRestController(AbilityServiceImpl abilityServiceImpl) {
        this.abilityServiceImpl = abilityServiceImpl;
    }
    @Operation(
            description = "Get endpoint to retrieve all abilities that a user can have. This includes abilities such as specific permissions or skills that can be assigned to users.",
            summary = "Retrieve all abilities",
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
    @GetMapping("/abilities")
    public ResponseEntity<List<Ability>> findAll(){
        return new ResponseEntity<>(abilityServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve an ability by its ID. This is useful for fetching the details of a specific ability that a user might possess.",
            summary = "Retrieve an ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/abilities/{abilityId}")
    public ResponseEntity<Optional<Ability>> findById(@PathVariable Long abilityId){
        return new ResponseEntity<>(abilityServiceImpl.findById(abilityId), HttpStatus.OK);
    }
    @Operation(
            description = "Post endpoint to add a new ability. This allows for the creation of new abilities that can be assigned to users.",
            summary = "Add a new ability",
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

    @PostMapping("/abilities")
    public ResponseEntity<Ability> create(@RequestBody PostAbilityDto postAbilityDto){
        return new ResponseEntity<>(abilityServiceImpl.create(postAbilityDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing ability. This can be used to modify the details of an ability, such as its name or associated permissions.",
            summary = "Update an ability",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/abilities/{abilityId}")
    public ResponseEntity<Ability> update(@PathVariable Long abilityId,@RequestBody PutAbilityDto putAbilityDto){
        return new ResponseEntity<>(abilityServiceImpl.update(abilityId, putAbilityDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove an ability by its ID. This is useful for deleting a specific ability that is no longer needed.",
            summary = "Delete an ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Ability not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/abilities/{abilityId}")
    public ResponseEntity<String> deleteById(@PathVariable Long abilityId){
        return new ResponseEntity<>(abilityServiceImpl.deleteById(abilityId), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove all abilities. This is useful for bulk deletion of all abilities in the system.",
            summary = "Delete all abilities",
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
    @DeleteMapping("/abilities")
    public String deleteAll(){
        return abilityServiceImpl.deleteAll();
    }
}
