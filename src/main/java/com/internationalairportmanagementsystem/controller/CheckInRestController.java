package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostCheckInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCheckInDto;
import com.internationalairportmanagementsystem.enetity.CheckIn;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairportmanagementsystem.service.interfaces.CheckInService;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private")
public class CheckInRestController {

    private CheckInService checkInService;

    private UserEntityService userEntityService;

    @Autowired
    public CheckInRestController(CheckInService theCheckInService, UserEntityService userEntityService){

        checkInService = theCheckInService;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Get endpoint to retrieve all check-ins",
            summary = "Retrieve all check-ins",
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
    @GetMapping("/check_ins")
    public List<CheckIn> findAllCheckIns(){

        UserEntity user = getAuthenticatedUser();

        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            return checkInService.findByPassengerId(passenger.getPassengerId());
        }

        return checkInService.findAll();
    }

    @Operation(
            description = "Get endpoint to retrieve a check-in by its ID",
            summary = "Retrieve a check-in by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Check-in not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/check_ins/{checkInId}")
    public CheckIn getCheckInById(@PathVariable Long checkInId){
        CheckIn theCheckIn = checkInService.findById(checkInId);
        if(theCheckIn == null){
            throw new RuntimeException("Check In Id not found - " + checkInId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theCheckIn);
        return theCheckIn;
    }

    @Operation(
            description = "Post endpoint to add a new check-in",
            summary = "Add a new check-in",
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
    @PostMapping("/check_ins")
    public CheckIn addCheckIn(@RequestBody PostCheckInDto postCheckInDto){
        return checkInService.create(postCheckInDto);
    }

    @Operation(
            description = "Put endpoint to update an existing check-in",
            summary = "Update a check-in",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Check-in not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/check_ins")
    public CheckIn updateCheckIn(@RequestBody PutCheckInDto putCheckInDto){
        return checkInService.update(putCheckInDto);
    }

    @Operation(
            description = "Delete endpoint to remove a check-in by its ID",
            summary = "Delete a check-in by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Check-in not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/check_ins/{checkInId}")
    public String deleteCheckInById(@PathVariable Long checkInId){
        CheckIn tempCheckIn = checkInService.findById(checkInId);
        if(tempCheckIn == null){
            throw new RuntimeException("Id not found - " + checkInId);
        }
        checkInService.deleteById(checkInId);
        return "Deleted Check In id - " + checkInId;
    }

    @Operation(
            description = "Delete endpoint to remove all check-ins",
            summary = "Delete all check-ins",
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
    @DeleteMapping("/check_ins")
    public String deleteAllCheckIns() {
        return checkInService.deleteAll();
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, CheckIn checkIn) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (passenger.getPassengerId() != checkIn.getPassenger().getPassengerId()) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}
