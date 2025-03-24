package com.internationalairportmanagementsystem.controller;


import com.internationalairportmanagementsystem.dtos.posts.PostAirlineDto;
import com.internationalairportmanagementsystem.enetity.Airline;
import com.internationalairportmanagementsystem.repository.AirlineRepository;
import com.internationalairportmanagementsystem.service.interfaces.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class AirlineServiceRestController {

    private AirlineRepository airlineRepository;
    private AirlineService airlineService;


    @Autowired
    public AirlineServiceRestController( AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<AirlineService> create(@RequestBody PostAirlineDto postAirlineDto){
        return airlineService.create(postAirlineDto);
    }



    // update
    // findAll
    // findById
    // deleteById
    // deleteAll
}
