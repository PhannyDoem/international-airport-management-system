package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostParkingDto;
import com.internationalairportmanagementsystem.dtos.puts.PutParkingDto;
import com.internationalairportmanagementsystem.enetity.Parking;

import java.util.List;

public interface ParkingService {
    Parking create(PostParkingDto postParkingDto);
    Parking update(Long parkingId, PutParkingDto putParkingDto);
    List<Parking> findAll();
    Parking findById(Long parkingId);
    String deleteById(Long parkingId);
    String deleteAllParking();
}
