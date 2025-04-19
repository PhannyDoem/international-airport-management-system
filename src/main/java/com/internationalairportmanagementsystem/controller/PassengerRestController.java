package com.internationalairportmanagementsystem.controller;


import com.internationalairportmanagementsystem.dtos.AuthResponseDto;
import com.internationalairportmanagementsystem.dtos.posts.PostLongInDto;
import com.internationalairportmanagementsystem.dtos.posts.PostPassengerDto;
import com.internationalairportmanagementsystem.dtos.puts.PutPassengerDto;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import com.internationalairportmanagementsystem.security.JWTGenerator;
import com.internationalairportmanagementsystem.service.implementations.PassengerServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class PassengerRestController {
    private final PassengerServiceImpl passengerServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userRepository;
    private final JWTGenerator jwtGenerator;
    private final UserEntityService userEntityService;
    @Autowired
    public PassengerRestController(PassengerServiceImpl passengerServiceImpl,
                                   AuthenticationManager authenticationManager,
                                   UserEntityRepository userRepository,
                                   JWTGenerator jwtGenerator,
                                   UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtGenerator=jwtGenerator;
        this.passengerServiceImpl = passengerServiceImpl;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Endpoint to get all passengers",
            summary = "Retrieve all passengers",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all passengers",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/passengers")
    public List<Passenger> findAll() {
        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = passengerServiceImpl.findById(user.getPassenger().getPassengerId());
            return Collections.singletonList(passenger);
        }
        return passengerServiceImpl.findAll();
    }

    @Operation(
            description = "Endpoint to get a passenger by ID",
            summary = "Retrieve a passenger by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Passenger ID not found or unauthorized access",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/private/passengers/{passengerId}")
    public Passenger getPassenger(@PathVariable Long passengerId) {
        Passenger thePassenger = passengerServiceImpl.findById(passengerId);
        if (thePassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, thePassenger);
        return thePassenger;
    }

    @Operation(
            description = "Endpoint to log in a passenger",
            summary = "Login a passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully logged in",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid credentials",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/public/auth/passengers/login")
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
            description = "Endpoint to register a new passenger",
            summary = "Register a new passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully registered the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username already taken",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/public/auth/passengers/register")
    public ResponseEntity<String> register(@RequestBody PostPassengerDto postPassengerDto) {
        if (userRepository.existsByUsername(postPassengerDto.userEntity().getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        passengerServiceImpl.create(postPassengerDto);

        return new ResponseEntity<>("Passenger registered successfully!", HttpStatus.OK);
    }


    @Operation(
            description = "Endpoint to update a passenger",
            summary = "Update a passenger",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized access or invalid request",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/passengers/{passengerId}")
    public ResponseEntity<String> updatePassenger(@PathVariable Long passengerId,@RequestBody PutPassengerDto putPassengerDto) {

        Passenger thePassenger = passengerServiceImpl.update(passengerId,putPassengerDto);
        UserEntity user = thePassenger.getUserEntity();

        if (userEntityService.existsByUsername(putPassengerDto.userEntity().getUsername()) &&
                !putPassengerDto.userEntity().getUsername().equals(user.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user1 = getAuthenticatedUser();
        authorizeAccess(user1, thePassenger);
        passengerServiceImpl.update(passengerId, putPassengerDto);

        return new ResponseEntity<>("Passenger updated successfully!", HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete a passenger by ID",
            summary = "Delete a passenger by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully deleted the passenger",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized access or invalid request",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Passenger ID not found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/private/passengers/{passengerId}")
    public String deletePassenger(@PathVariable Long passengerId) {
        Passenger tempPassenger = passengerServiceImpl.findById(passengerId);
        if (tempPassenger == null) {
            throw new RuntimeException("Passenger id not found - " + passengerId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, tempPassenger);
        passengerServiceImpl.deleteById(passengerId);
        return "Deleted passenger id - " + passengerId;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }

    private void authorizeAccess(UserEntity user, Passenger thepassenger) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (!Objects.equals(passenger.getPassengerId(), thepassenger.getPassengerId())) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }

}