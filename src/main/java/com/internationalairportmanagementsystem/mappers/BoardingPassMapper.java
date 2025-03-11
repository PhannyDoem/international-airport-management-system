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
                postBoardingPassDto.boardingTime()
        );
        boardingPass.setBoardingPassId(0L);
        if (postBoardingPassDto.ticketId() != null) {
            Ticket ticket = new Ticket();
            ticket.setTicketId(postBoardingPassDto.ticketId());
            boardingPass.setTicket(ticket);
        }
        return boardingPass;
    }

    public BoardingPass putToBoardingPass(PutBoardingPassDto putBoardingPassDto) {
        BoardingPass boardingPass = new BoardingPass(
                putBoardingPassDto.gate(),
                putBoardingPassDto.boardingTime()
        );
        boardingPass.setBoardingPassId(putBoardingPassDto.boardingPassId());

        if(putBoardingPassDto.ticketId() != null) {
            Ticket ticket = new Ticket();
            ticket.setTicketId(putBoardingPassDto.ticketId());
            boardingPass.setTicket(ticket);
        }
        return boardingPass;
    }
}
