package com.internationalairportmanagementsystem.controller;

import com.internationalairportmanagementsystem.dtos.AuthResponseDto;
import com.internationalairportmanagementsystem.dtos.posts.PostEmployeeDto;
import com.internationalairportmanagementsystem.dtos.posts.PostLongInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutEmployeeDto;
import com.internationalairportmanagementsystem.enetity.Employee;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.security.JWTGenerator;
import com.internationalairportmanagementsystem.service.implementations.EmployeeServiceImpl;
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

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeServiceImpl employeeServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private final UserEntityService userEntityService;
    @Autowired
    public EmployeeRestController(EmployeeServiceImpl employeeServiceImpl,
                                  AuthenticationManager authenticationManager,
                                  JWTGenerator jwtGenerator,
                                  UserEntityService userEntityService) {
        this.employeeServiceImpl = employeeServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator=jwtGenerator;
        this.userEntityService = userEntityService;
    }

    @Operation(
            description = "Endpoint to log in an employee",
            summary = "Login for employees",
            responses = {
                    @ApiResponse(
                            description = "Successful login",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PostMapping("/public/auth/employees/login")
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
            description = "Endpoint to register a new employee",
            summary = "Register new employee",
            responses = {
                    @ApiResponse(
                            description = "Successful registration",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Username is taken",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping("/private/auth/employees/register")
    public ResponseEntity<String> register(@RequestBody PostEmployeeDto postEmployeeDto) {
        if (userEntityService.existsByUsername(postEmployeeDto.userEntity().getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        employeeServiceImpl.create(postEmployeeDto);

        return new ResponseEntity<>("Employee registered successfully!", HttpStatus.OK);
    }
    @Operation(
            description = "Endpoint to retrieve all employees",
            summary = "Retrieve all employees",
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
    @GetMapping("/private/employees")
    public List<Employee> findAll() {
        UserEntity user = getAuthenticatedUser();
        if (isEmployee(user)) {
            Employee employee = employeeServiceImpl.findById(user.getEmployee().getEmployeeId());
            return Collections.singletonList(employee);
        }

        return employeeServiceImpl.findAll();
    }
    @Operation(
            description = "Endpoint to retrieve an employee by ID",
            summary = "Retrieve an employee by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @GetMapping("/private/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeServiceImpl.findById(employeeId), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to update an employee",
            summary = "Update an employee",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @PutMapping("/private/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId,@RequestBody PutEmployeeDto putEmployeeDto) {
        return new ResponseEntity<>(employeeServiceImpl.update(employeeId, putEmployeeDto), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint to delete an employee by ID",
            summary = "Delete an employee by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/private/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeServiceImpl.deleteById(employeeId), HttpStatus.OK);
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userEntityService.findByUsername(username);
    }

    private boolean isEmployee(UserEntity user) {
        return user.getRole().getRoleName().equals("EMPLOYEE");
    }

}
