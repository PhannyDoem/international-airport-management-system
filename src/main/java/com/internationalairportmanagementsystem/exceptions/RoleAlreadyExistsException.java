package com.internationalairportmanagementsystem.exceptions;

public class RoleAlreadyExistsException extends RuntimeException{
    public RoleAlreadyExistsException(String message){
        super(message);
    }
}
