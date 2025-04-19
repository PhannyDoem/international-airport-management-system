package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.posts.PostBoardingPassDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBoardingPassDto;
import com.internationalairportmanagementsystem.enetity.BoardingPass;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.exceptions.AuthorizationException;
import com.internationalairportmanagementsystem.service.implementations.BoardingPassServiceImpl;
import com.internationalairportmanagementsystem.service.interfaces.BoardingPassService;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/private")
public class BoardingPassRestController {

    private final BoardingPassServiceImpl boardingPassServiceImpl;

    private final UserEntityService userEntityService;

    @Autowired
    public BoardingPassRestController(BoardingPassServiceImpl theBoardingPassServiceImpl, UserEntityService userEntityService){
        this.boardingPassServiceImpl = theBoardingPassServiceImpl;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Get endpoint to retrieve all boarding pass records. If the user is a passenger, it returns only their boarding passes.",
            summary = "Retrieve all boarding pass records",
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
    @GetMapping("/boarding/pass")
    public ResponseEntity<List<BoardingPass>> findAll(){
        return new ResponseEntity<>(boardingPassServiceImpl.findAll(), HttpStatus.OK);
    }

    @Operation(
            description = "Get endpoint to retrieve a boarding pass by its ID. Only the owner of the boarding pass (if a passenger) or an authorized user can access it.",
            summary = "Retrieve a boarding pass by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/boarding/passes/{boarding_passId}")
    public BoardingPass getBoardingPass(@PathVariable Long boarding_passId){

        BoardingPass theBoardingPass = boardingPassServiceImpl.findById(boarding_passId);
        if(theBoardingPass == null){
            throw new RuntimeException("Boarding Pass id not found - " + boarding_passId);
        }
        UserEntity user = getAuthenticatedUser();
        authorizeAccess(user, theBoardingPass);
        return theBoardingPass;
    }

    @Operation(
            description = "Post endpoint to add a new boarding pass. This allows for the creation of a new boarding pass.",
            summary = "Add a new boarding pass",
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
    @PostMapping("/boarding/passes")
    public ResponseEntity<BoardingPass> addBoardingPass(@RequestBody PostBoardingPassDto postBoardingPassDto){
        return new ResponseEntity<>(boardingPassServiceImpl.create(postBoardingPassDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Put endpoint to update an existing boarding pass. Only the owner of the boarding pass (if a passenger) or an authorized user can update it.",
            summary = "Update a boarding pass",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/boarding/passes/{boardingId}")
    public ResponseEntity<BoardingPass> updateBoardingPass(@PathVariable Long boardingId,@RequestBody PutBoardingPassDto putBoardingPassDto){
        return new ResponseEntity<>(boardingPassServiceImpl.update(boardingId, putBoardingPassDto), HttpStatus.OK);
    }

    @Operation(
            description = "Delete endpoint to remove a boarding pass by its ID. Only the owner of the boarding pass (if a passenger) or an authorized user can delete it.",
            summary = "Delete a boarding pass by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Boarding Pass not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/boarding_passes/{boarding_passId}")
    public ResponseEntity<String>deleteBoardingPass(@PathVariable Long boarding_passId){
        return new ResponseEntity<>(boardingPassServiceImpl.deleteById(boarding_passId), HttpStatus.OK);
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isPassenger(UserEntity user) {
        return user.getRole().getRoleName().equals("PASSENGER");
    }


    private void authorizeAccess(UserEntity user, BoardingPass boardingPass) {
        if (isPassenger(user)) {
            Passenger passenger = user.getPassenger();
            if (!Objects.equals(passenger.getPassengerId(), boardingPass.getTicket().getPassenger().getPassengerId())) {
                throw new AuthorizationException("You don't have access to this resource");
            }
        }
    }
}