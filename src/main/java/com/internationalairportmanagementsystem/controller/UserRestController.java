package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.AuthResponseDto;
import com.internationalairportmanagementsystem.dtos.posts.PostLongInDto;
import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.security.JWTGenerator;
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

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;
    private UserEntityService userEntityService;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserEntityService userEntityService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userEntityService = userEntityService;
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
                            description = "Invalid credentails",
                            responseCode = "401"
                    )
            }
    )

    @PostMapping("public/auth/user/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody PostLongInDto postLongInDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLongInDto.username(),
                        postLongInDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @Operation(
            description = "Register a new user",
            summary = "Endpoint register new user",
            responses = {
                    @ApiResponse(
                            description = "Successfully register new user",
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


    @PostMapping("/public/auth/user/register")
    public ResponseEntity<String> register(@RequestBody PostUserDto postUserDto){
        if (userEntityService.existsByUsername(postUserDto.username())){
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        userEntityService.create(postUserDto);
        return new ResponseEntity<>("Successfully created user", HttpStatus.OK);
    }

    @Operation(
            description = "Get all users private endpoint",
            summary = "Get all user endpoint",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved all user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/users")
    public List<UserEntity> findAllUsers(){
        return userEntityService.findAll();
    }

    @Operation(
            description = "Get a user by ID, private endpoint",
            summary = "Endpoint to get user by ID",
            responses = {
                    @ApiResponse(
                            description = "Successfully retrieved the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User ID  not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/user/id/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        UserEntity userEntity = userEntityService.findById(id);
        if (userEntity == null){
            throw new RuntimeException("User not found");
        }
        return userEntity;
    }
    @Operation(
            description = "Get user by username",
            summary = "Endpoint to get user",
            responses = {
                    @ApiResponse(
                            description = "Successfully get the user",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username not found or Username is taken",
                            responseCode = "404 or 400"
                    ),
                    @ApiResponse(
                            description = "Access unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("private/user/username/(username)")
    public UserEntity getUserByUsername(@RequestParam String username){
        UserEntity userEntity = userEntityService.findByUsername(username);
        if (userEntity == null){
            throw new RuntimeException("User not found");
        }
        return userEntity;
    }
    @Operation(
            description = "Update user private endpoint",
            summary = "Endpoint to update",
            responses = {
                    @ApiResponse(
                            description = "Successfully update the user",
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

    @PutMapping("/private/users")
    public ResponseEntity<String> updateUser(@RequestBody PutUserDto putUserDto){
        UserEntity userEntity = userEntityService.findById(putUserDto.userId());
        if (userEntityService.existsByUsername(putUserDto.username()) &&
                !putUserDto.username().equals(userEntity.getUsername())){
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        userEntityService.update(putUserDto);
        return new ResponseEntity<>("Successfully updated user", HttpStatus.OK);
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
    @DeleteMapping("/private/users/{id}")
    public String deleteUserById(@PathVariable Long id){
        UserEntity userEntity = userEntityService.findById(id);
        if (userEntity == null){
            throw new RuntimeException("User not found");
        }
        userEntityService.deleteById(id);
        return "Successfully deleted user";
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
    public String deleteAllUsers(){
        return userEntityService.deleteAll();
    }

}
