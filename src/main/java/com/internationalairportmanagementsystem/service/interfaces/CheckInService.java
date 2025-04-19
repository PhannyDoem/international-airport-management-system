package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostCheckInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCheckInDto;
import com.internationalairportmanagementsystem.enetity.CheckIn;

import java.util.List;

public interface CheckInService {
    CheckIn create(PostCheckInDto  postCheckInDto);

    CheckIn update(Long checkInId,PutCheckInDto putCheckInDto);

    CheckIn findById(Long checkInId);

    List<CheckIn>  findAll();

    CheckIn  findByPassengerId(Long passengerId);

    String deleteById(Long checkInId);

    String deleteAll();

}
