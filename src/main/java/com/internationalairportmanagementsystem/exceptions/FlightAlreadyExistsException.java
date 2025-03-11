package com.internationalairportmanagementsystem.exceptions;

public class FlightAlreadyExistsException extends RuntimeException{
    public FlightAlreadyExistsException(String message){
        super(message);
    }
}
