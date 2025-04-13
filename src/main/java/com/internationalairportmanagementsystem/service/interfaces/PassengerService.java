package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostPassengerDto;
import com.internationalairportmanagementsystem.dtos.puts.PutPassengerDto;
import com.internationalairportmanagementsystem.enetity.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger create(PostPassengerDto postPassengerDto);
    Passenger update(Long passengerId, PutPassengerDto putPassengerDto);
    List<Passenger> findAll();
    Passenger findById(Long passengerId);
    Passenger findByUserEntityId(Long userEntityId);
    String deleteById(Long passengerId);
}
