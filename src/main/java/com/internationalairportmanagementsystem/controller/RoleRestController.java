package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.exceptions.RoleAlreadyExistsException;
import com.internationalairportmanagementsystem.service.implementations.RoleServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class RoleRestController {

    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public RoleRestController( RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @Operation(
            description = "Endpoint to retrieve all roles",
            summary = "Get all roles",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all roles",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/roles")
    public List<Role> findAllRoles() {
        return roleServiceImpl.findAll();
    }

    @Operation(
            description = "Endpoint to retrieve a role by ID",
            summary = "Get a role by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the role",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Role ID not found",
                            responseCode = "404"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            )
            }
    )
    @GetMapping("/roles/{RoleId}")
    public Role getRoleById(@PathVariable Long RoleId) {
        return roleServiceImpl.findById(RoleId);
    }

    @Operation(
            description = "Endpoint to add a new role",
            summary = "Add a new role",
            responses = {
                    @ApiResponse(
                            description = "Successfully added the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            )
            }
    )
    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody PostRoleDto postRoleDto) {
        return new ResponseEntity<>(roleServiceImpl.create(postRoleDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to update a role",
            summary = "Update a role",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            )
            }
    )
    @PutMapping("/roles/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Role roleId, @RequestBody PutRoleDto putRoleDto) {
        return new ResponseEntity<>(roleServiceImpl.update(roleId.getRoleId(), putRoleDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a role by ID",
            summary = "Delete a role by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the role",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            ),
                    @ApiResponse(
                            description = "Role ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<String> deleteRoleById(@PathVariable Long roleId) {
        return new ResponseEntity<>(roleServiceImpl.deleteById(roleId),  HttpStatus.OK);
    }


    @Operation(
            description = "Endpoint to delete all roles",
            summary = "Delete all roles",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all roles",
                            responseCode = "200"
                    ),@ApiResponse(
                    description = "Access unauthorized",
                    responseCode = "401"
            ),
            }
    )
    @DeleteMapping("/roles")
    public String deleteAllRoles() {
        return roleServiceImpl.deleteAll();
    }
}