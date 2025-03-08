package com.internationalairportmanagementsystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Healty {
    @GetMapping("/need-sleep")
    public String hello(){
        return "Please, Go to sleep before at 12:00 pm";
    }

}
