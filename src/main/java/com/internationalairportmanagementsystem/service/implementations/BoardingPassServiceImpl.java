package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostBoardingPassDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBoardingPassDto;
import com.internationalairportmanagementsystem.enetity.BoardingPass;
import com.internationalairportmanagementsystem.mappers.BoardingPassMapper;
import com.internationalairportmanagementsystem.repository.BoardingPassRepository;
import com.internationalairportmanagementsystem.service.interfaces.BoardingPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardingPassServiceImpl implements BoardingPassService {

    private BoardingPassRepository boardingPassRepository;

    private BoardingPassMapper boardingPassMapper;

    @Autowired
    public BoardingPassServiceImpl(BoardingPassRepository theBoardingPassRepository, BoardingPassMapper theBoardingPassMapper){
        boardingPassRepository = theBoardingPassRepository;
        boardingPassMapper = theBoardingPassMapper;
    }
    @Override
    public BoardingPass create(PostBoardingPassDto postBoardingPassDto) {
        BoardingPass boardingPass = boardingPassMapper.postToBoardingPass(postBoardingPassDto);
        return boardingPassRepository.save(boardingPass);
    }

    @Override
    public BoardingPass update(Long boardingPassId, PutBoardingPassDto putBoardingPassDto) {
        BoardingPass boardingPass = boardingPassMapper.putToBoardingPass(putBoardingPassDto);
        return boardingPassRepository.save(boardingPass);
    }

    @Override
    public BoardingPass findById(Long theId) {
        Optional<BoardingPass> result = boardingPassRepository.findById(theId);
        BoardingPass theBoardingPass = null;
        if (result.isPresent()) {
            theBoardingPass = result.get();
        }
        else {
            throw new RuntimeException("Did not find Boarding Pass id - " + theId);
        }
        return theBoardingPass;
    }

    @Override
    public List<BoardingPass> findByPassengerId(Long passengerId) {
        return boardingPassRepository.findByPassengerId(passengerId);
    }

    @Override
    public List<BoardingPass> findAll() {
        return boardingPassRepository.findAll();
    }

    @Override
    public String deleteById(Long theId) {
        boardingPassRepository.deleteById(theId);
        return "Deleted";
    }
}