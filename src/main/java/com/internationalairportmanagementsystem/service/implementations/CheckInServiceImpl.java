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
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {
    private CheckInRepository checkInRepository;
    private CheckInMapper checkInMapper;
    @Autowired
    public  CheckInServiceImpl(CheckInRepository checkInRepository, CheckInMapper checkInMapper) {
        this.checkInRepository = checkInRepository;
        this.checkInMapper = checkInMapper;
    }

    @Override
    public CheckIn create(PostCheckInDto postCheckInDto) {
        CheckIn checkIn = checkInMapper.postToCheckIn(postCheckInDto);
        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn update(PutCheckInDto putCheckInDto) {
        CheckIn checkIn = checkInMapper.putToCheckIn(putCheckInDto);
        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn findById(Long checkInId) {
        Optional<CheckIn> result = checkInRepository.findById(checkInId);
        CheckIn checkIn = null;
        if(result.isPresent()) {
            checkIn = result.get();
        }else {
            throw new RuntimeException("CheckIn Not Found");
        }
        return checkIn;
    }

    @Override
    public List<CheckIn> findAll() {
        return checkInRepository.findAll();
    }

    @Override
    public List<CheckIn> findByPassengerId(Long passengerId) {
        return checkInRepository.findByPassengerId(passengerId);
    }

    @Override
    public String deleteById(Long checkInId) {
        checkInRepository.deleteById(checkInId);
        return "Deleted CheckIn with Id " + checkInId;
    }

    @Override
    public String deleteAll() {
        checkInRepository.deleteAll();
        return "Deleted All CheckIns";
    }
}
