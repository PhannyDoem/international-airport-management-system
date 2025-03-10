package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightScheduleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightScheduleDto;
import com.internationalairportmanagementsystem.enetity.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {
    FlightSchedule create(PostFlightScheduleDto  postFlightScheduleDto);
    FlightSchedule update(PutFlightScheduleDto putFlightScheduleDto);
    FlightSchedule findById(Long scheduleId);
    List<FlightSchedule> findAll();
    String deleteById(Long scheduleId);
    String deleteAll();
}
