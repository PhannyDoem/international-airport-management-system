package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostBaggageDto;
import com.internationalairportmanagementsystem.dtos.puts.PutBaggageDto;
import com.internationalairportmanagementsystem.enetity.Baggage;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.Passenger;
import org.springframework.stereotype.Service;

@Service
public class BaggageMapper {
    public Baggage postToBaggage(PostBaggageDto postBaggageDto) {
        Baggage baggage = new Baggage(
                postBaggageDto.weight(),
                postBaggageDto.flight(),
                postBaggageDto.passenger()
        );
        baggage.setBaggageId(0L);
        return baggage;
    }

    public  Baggage putToBaggage(PutBaggageDto putBaggageDto) {
        Baggage baggage = new Baggage(
                putBaggageDto.weight(),
                putBaggageDto.flight(),
                putBaggageDto.passenger()
        );
        baggage.setBaggageId(0L);
        return baggage;
    }
}
