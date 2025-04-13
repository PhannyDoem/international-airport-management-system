package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostBoardingPassDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBoardingPassDto;
import com.internationalairportmanagementsystem.enetity.BoardingPass;
import com.internationalairportmanagementsystem.enetity.Ticket;
import org.springframework.stereotype.Service;

@Service
public class BoardingPassMapper {
    public BoardingPass postToBoardingPass(PostBoardingPassDto postBoardingPassDto) {
        BoardingPass boardingPass = new BoardingPass(
                postBoardingPassDto.gate(),
                postBoardingPassDto.boardingTime(),
                postBoardingPassDto.ticket()
        );
        boardingPass.setBoardingPassId(0L);
        return boardingPass;
    }

    public BoardingPass putToBoardingPass(PutBoardingPassDto putBoardingPassDto) {
        BoardingPass boardingPass = new BoardingPass(
                putBoardingPassDto.gate(),
                putBoardingPassDto.boardingTime(),
                putBoardingPassDto.ticket()
        );
        boardingPass.setBoardingPassId(0L);
        return boardingPass;
    }
}
