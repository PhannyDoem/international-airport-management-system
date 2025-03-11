package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostParkingDto;
import com.internationalairportmanagementsystem.dtos.puts.PutParkingDto;
import com.internationalairportmanagementsystem.enetity.Parking;
import org.springframework.stereotype.Service;

@Service
public class ParkingMapper {
    public Parking postToParking(PostParkingDto  postParkingDto) {
        Parking parking = new Parking(
                postParkingDto.location(),
                postParkingDto.capacity(),
                postParkingDto.rate()
        );
        parking.setPartId(0L);
        return parking;
    }
    public Parking putToParking(PutParkingDto putParkingDto) {
        Parking parking = new Parking(
                putParkingDto.location(),
                putParkingDto.capacity(),
                putParkingDto.rate()
        );
        parking.setPartId(putParkingDto.parkingId());
        return parking;
    }
}
