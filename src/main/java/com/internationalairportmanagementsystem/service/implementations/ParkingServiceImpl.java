package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostParkingDto;
import com.internationalairportmanagementsystem.dtos.puts.PutParkingDto;
import com.internationalairportmanagementsystem.enetity.Parking;
import com.internationalairportmanagementsystem.mappers.ParkingMapper;
import com.internationalairportmanagementsystem.repository.ParkingRepository;
import com.internationalairportmanagementsystem.service.interfaces.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;
    private final ParkingMapper  parkingMapper;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository, ParkingMapper parkingMapper) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
    }


    @Override
    public Parking create(PostParkingDto postParkingDto) {
        Parking parking = parkingMapper.postToParking(postParkingDto);
        return parkingRepository.save(parking);
    }

    @Override
    public Parking update(Long parkingId, PutParkingDto putParkingDto) {
        if (parkingId != null){
            parkingRepository.findById(parkingId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setCapacity(putParkingDto.capacity());
                                updated.setLocation(putParkingDto.location());
                                updated.setRate(putParkingDto.rate());
                                Parking parking = parkingRepository.save(updated);
                                return parkingRepository.save(parking);
                            }
                    );
        }
        return parkingRepository.findById(Objects.requireNonNull(parkingId))
                .orElseThrow(()-> new RuntimeException("Updated Parking not found"));
    }

    @Override
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking findById(Long parkingId) {
        return parkingRepository.findById(parkingId).orElse(null);
    }

    @Override
    public String deleteById(Long parkingId) {
        parkingRepository.deleteById(parkingId);
        return "Delete Parking Successfully";
    }

    @Override
    public String deleteAllParking() {
        parkingRepository.deleteAll();
        return "Delete All Parking Successfully";
    }
}
