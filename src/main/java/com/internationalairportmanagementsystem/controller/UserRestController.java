package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.AuthResponseDto;
import com.internationalairportmanagementsystem.dtos.posts.PostLongInDto;
import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.security.JWTGenerator;
import com.internationalairportmanagementsystem.service.implementations.UserEntityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserEntityServiceImpl userEntityServiceImpl;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager,
                              JWTGenerator jwtGenerator,
                              UserEntityServiceImpl userEntityServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userEntityServiceImpl = userEntityServiceImpl;
    }

    @Operation(
            description = "Login to the system",
            summary = "Endpoint to login",
            responses = {
                    @ApiResponse(
                            description = "Successfully logged in and received authentication token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid credentials",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/public/auth/users/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody PostLongInDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @Operation(
            description = "Register a new user",
            summary = "Endpoint to register a new user",
            responses = {
                    @ApiResponse(
                            description = "Successfully registered a new user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username is already taken",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/public/auth/users/register")
    public ResponseEntity<String> register(@RequestBody PostUserDto postUserDto) {
        if (userEntityServiceImpl.existsByUsername(postUserDto.username())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        userEntityServiceImpl.create(postUserDto);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @Operation(
            description = "Get all users (private endpoint)",
            summary = "Get all users endpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all users",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/users")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return new ResponseEntity<>(userEntityServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get a user by ID (private endpoint)",
            summary = "Endpoint to get user by ID ",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/users/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
       return new ResponseEntity<>(userEntityServiceImpl.findById(userId), HttpStatus.OK);
    }

    @Operation(
            description = "Update a user (private endpoint)",
            summary = "Endpoint to update user",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found or Username is taken",
                            responseCode = "404 or 400"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )

    @GetMapping("/private/users/username/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        UserEntity user = userEntityServiceImpl.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found for username - " + username);
        }
        return user;
    }

    @PutMapping("/private/users/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long userId, @RequestBody PutUserDto putUserDto) {
        return new ResponseEntity<>(userEntityServiceImpl.update(userId,putUserDto), HttpStatus.OK);
    }


    @Operation(
            description = "Delete a user by ID (private endpoint)",
            summary = "Endpoint to delete user by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
       return new ResponseEntity<>(userEntityServiceImpl.deleteById(userId), HttpStatus.OK);
    }

    @Operation(
            description = "Delete all users (private endpoint)",
            summary = "Endpoint to delete all users",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted all users",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/users")
    public ResponseEntity<String> deleteAllUsers() {
        return new ResponseEntity<>(userEntityServiceImpl.deleteAll(), HttpStatus.OK);
    }
}