package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostCheckInDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCheckInDto;
import com.internationalairportmanagementsystem.enetity.CheckIn;
import com.internationalairportmanagementsystem.mappers.CheckInMapper;
import com.internationalairportmanagementsystem.repository.CheckInRepository;
import com.internationalairportmanagementsystem.service.interfaces.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CheckInServiceImpl implements CheckInService {
    private final CheckInRepository checkInRepository;
    private final CheckInMapper checkInMapper;

    @Autowired
    public CheckInServiceImpl(CheckInRepository checkInRepository, CheckInMapper checkInMapper) {
        this.checkInRepository = checkInRepository;
        this.checkInMapper = checkInMapper;
    }


    @Override
    public CheckIn create(PostCheckInDto postCheckInDto) {
        CheckIn checkIn = checkInMapper.postToCheckIn(postCheckInDto);
        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn update(Long checkInId, PutCheckInDto putCheckInDto) {
        CheckIn checkIn = checkInMapper.putToCheckIn(putCheckInDto);
        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn findById(Long checkInId) {
        if (checkInId != null){
            checkInRepository.findById(checkInId);
        }
        return checkInRepository.findById(Objects.requireNonNull(checkInId)).orElse(null);
    }

    @Override
    public List<CheckIn> findAll() {
        return checkInRepository.findAll();
    }

    @Override
    public CheckIn findByPassengerId(Long passengerId) {
        if (passengerId != null){
            checkInRepository.findByPassengerId(passengerId);
        }
        return checkInRepository.findById(Objects.requireNonNull(passengerId))
                .orElseThrow(()-> new RuntimeException("Find Passenger with Id " + passengerId + " not found"));
    }

    @Override
    public String deleteById(Long checkInId) {
        if (checkInId != null){
             checkInRepository.deleteById(checkInId);
        }else
            return "Delete CheckIn with Id " + null + " not found";
        return "Deleted CheckIn with Id " + checkInId;
    }

    @Override
    public String deleteAll() {
        checkInRepository.deleteAll();
        return "Deleted All CheckIns";
    }
}
