package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.exceptions.RoleAlreadyExistsException;
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
    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
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
    public Role addRole(@RequestBody PostRoleDto postRoleDto) {
        if (roleService.existsByRoleName(postRoleDto.roleName())){
            throw new RoleAlreadyExistsException("Role Already Exists");
        }
        return roleService.create(postRoleDto);
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
        return roleService.findAll();
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
    @GetMapping("/roles/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) {
        Role role = roleService.findById(roleId);
        if (role == null) {
            throw new RuntimeException("Role Not Found");
        }
        return role;
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
    @PutMapping("/roles")
    public Role updateRole(@RequestBody PutRoleDto putRoleDto) {
        Role role = roleService.findById(putRoleDto.roleId());
        if (roleService.existsByRoleName(putRoleDto.roleName()) &&
        !putRoleDto.roleName().equals(role.getRoleName())) {
            throw new RuntimeException("Role Name Already Exists");
        }
        return roleService.update(putRoleDto);
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
        Role role = roleService.findById(roleId);
        if (role == null) {
            throw new RuntimeException("Role Not Found");
        }
        roleService.deleteById(roleId);
        return new  ResponseEntity<>("Role Deleted Successfully", HttpStatus.OK);
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
       return roleService.deleteAll();
    }
}
