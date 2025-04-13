package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightScheduleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightScheduleDto;
import com.internationalairportmanagementsystem.enetity.FlightSchedule;
import com.internationalairportmanagementsystem.mappers.FlightScheduleMapper;
import com.internationalairportmanagementsystem.repository.FlightScheduleRepository;
import com.internationalairportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {
    private FlightScheduleRepository flightScheduleRepository;
    private FlightScheduleMapper flightScheduleMapper;

    @Autowired
    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository, FlightScheduleMapper flightScheduleMapper) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.flightScheduleMapper = flightScheduleMapper;
    }

    @Override
    public FlightSchedule create(PostFlightScheduleDto postFlightScheduleDto) {
        FlightSchedule flightSchedule = flightScheduleMapper.postToSchedule(postFlightScheduleDto);
       return flightScheduleRepository.save(flightSchedule);
    }

    @Override
    public FlightSchedule update(Long flightScheduleId, PutFlightScheduleDto putFlightScheduleDto) {
        FlightSchedule flightSchedule = flightScheduleMapper.putToSchedule(putFlightScheduleDto);
        return flightScheduleRepository.save(flightSchedule);
    }

    @Override
    public FlightSchedule findById(Long scheduleId) {
        return null;
    }

    @Override
    public List<FlightSchedule> findAll() {
        return flightScheduleRepository.findAll();
    }

    @Override
    public String deleteById(Long scheduleId) {
        flightScheduleRepository.deleteById(scheduleId);
        return "Deleted successfully";
    }

    @Override
    public String deleteAll() {
        return "Deleted all successfully";
    }
}
