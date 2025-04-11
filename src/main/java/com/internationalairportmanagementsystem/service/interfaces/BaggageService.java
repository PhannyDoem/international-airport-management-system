package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostBaggageDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBaggageDto;
import com.internationalairportmanagementsystem.enetity.Baggage;

import java.util.List;

public interface BaggageService {
    Baggage create(PostBaggageDto  postBaggageDto);

    Baggage update(Long baggageId,PutBaggageDto putBaggageDto);

    List<Baggage>  findAll();

    Baggage  findById(Long baggageId);

    String deleteById(Long baggageId);

    List<Baggage> findByPassengerId(Long passengerId);

    String deleteAll();
}
