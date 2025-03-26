package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostRentalServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRentalServiceDto;
import com.internationalairportmanagementsystem.enetity.RentalService;

import java.util.List;

public interface RentalServiceService {
    RentalService create(PostRentalServiceDto postRentalService);
    RentalService update(PutRentalServiceDto  putRentalServiceDto);
    List<RentalService> findAll();
    RentalService findById(Long rentalServiceId);
    String deleteById(Long rentalServiceId);
    String deleteAll();
}
