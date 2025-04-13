package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostBoardingPassDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBoardingPassDto;
import com.internationalairportmanagementsystem.enetity.BoardingPass;

import java.util.List;

public interface BoardingPassService {
    BoardingPass create(PostBoardingPassDto  postBoardingPassDto);

    BoardingPass update(Long boardingPassId, PutBoardingPassDto putBoardingPassDto);

    List<BoardingPass> findAll();

    BoardingPass findById(Long boardingPassId);

    List<BoardingPass> findByPassengerId(Long passengerId);

    String deleteById(Long borderingPassId);
}
