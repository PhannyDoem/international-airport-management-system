package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostCheckInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCheckInDto;
import com.internationalairportmanagementsystem.enetity.CheckIn;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CheckInMapper {
    public CheckIn postToCheckIn(PostCheckInDto postCheckInDto) {
        CheckIn checkIn = new CheckIn(
                postCheckInDto.checkInTime(),
                postCheckInDto.deskNumber(),
                postCheckInDto.passenger(),
                postCheckInDto.flight()
                );
        checkIn.setCheckInId(0L);
        return checkIn;
    }

    public CheckIn putToCheckIn(PutCheckInDto putCheckInDto) {
        CheckIn checkIn = new CheckIn(
                putCheckInDto.checkInTime(),
                putCheckInDto.deskNumber(),
                putCheckInDto.passenger(),
                putCheckInDto.flight()
        );
        checkIn.setCheckInId(0L);
        return checkIn;
    }
}
