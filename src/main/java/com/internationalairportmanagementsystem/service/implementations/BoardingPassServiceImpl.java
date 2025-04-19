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
import java.util.Objects;

@Service
public class BoardingPassServiceImpl implements BoardingPassService {

    private final BoardingPassRepository boardingPassRepository;

    private final BoardingPassMapper boardingPassMapper;

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
        if (boardingPassId != null){
            boardingPassRepository.findById(boardingPassId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setTicket(putBoardingPassDto.ticket());
                                updated.setBoardingTime(putBoardingPassDto.boardingTime());
                                updated.setGate(putBoardingPassDto.gate());
                                BoardingPass boarding = boardingPassRepository.save(updated);
                                return boardingPassRepository.save(boarding);
                            }
                    );
        }
        return boardingPassRepository.findById(Objects.requireNonNull(boardingPassId))
                .orElseThrow(()-> new RuntimeException("Update Boarding Pass with Id not found!"));
    }

    @Override
    public BoardingPass findById(Long boardingId) {
        return boardingPassRepository.findById(boardingId)
                .orElseThrow(()-> new RuntimeException("Find by id not found!"));
    }


    @Override
    public List<BoardingPass> findAll() {
        return boardingPassRepository.findAll();
    }

    @Override
    public String deleteById(Long boardingId) {
        boardingPassRepository.deleteById(boardingId);
        return "Deleted all Boarding Pass!";
    }
}