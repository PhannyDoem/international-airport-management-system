package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleAbilityDto;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.service.implementations.RoleAbilityServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.RoleAbilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/private")
public class RoleAbilityRestController {

    private final RoleAbilityServiceImpl roleAbilityServiceImpl;

    @Autowired
    public RoleAbilityRestController(RoleAbilityServiceImpl roleAbilityServiceImpl) {
        this.roleAbilityServiceImpl = roleAbilityServiceImpl;
    }


    @Operation(
            description = "Endpoint to add a new role ability",
            summary = "Add a new role ability",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the role ability",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/role_abilities")
    public ResponseEntity<Role> addRoleAbility(@RequestBody PostRoleAbilityDto postRoleAbilityDto) {
        return new ResponseEntity<>(roleAbilityServiceImpl.create(postRoleAbilityDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a role ability by ID",
            summary = "Delete a role ability by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the role ability",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Role ability ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/role_abilities/{roleId}/{abilityId}")
    public void deleteRoleAbilityById(@PathVariable Long roleId, @PathVariable Long abilityId) {
        roleAbilityServiceImpl.deleteByRoleIdAndAbilityId(roleId, abilityId);
    }
}