package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightScheduleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightScheduleDto;
import com.internationalairportmanagementsystem.enetity.FlightSchedule;
import com.internationalairportmanagementsystem.mappers.FlightScheduleMapper;
import com.internationalairportmanagementsystem.repository.FlightScheduleRepository;
import com.internationalairportmanagementsystem.service.interfaces.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {
    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightScheduleMapper flightScheduleMapper;

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
        if (flightScheduleId != null) {
             flightScheduleRepository.findById(flightScheduleId)
                    .stream()
                    .findFirst()
                    .map(
                        updatedFlightSchedule    -> {
                                updatedFlightSchedule.setFlight(putFlightScheduleDto.flight());
                                updatedFlightSchedule.setStatus(putFlightScheduleDto.status());
                                updatedFlightSchedule.setScheduledArrivalTime(putFlightScheduleDto.scheduledArrivalTime());
                                updatedFlightSchedule.setScheduledDepartureTime(putFlightScheduleDto.scheduledDepartureTime());
                                FlightSchedule flightSchedule = flightScheduleRepository.save(updatedFlightSchedule);
                                return flightScheduleRepository.save(flightSchedule);
                        }

                    );
        }
        return flightScheduleRepository.findById(Objects.requireNonNull(flightScheduleId))
                .orElseThrow(() -> new RuntimeException("Update Schedule with ID not found!"));
    }

    @Override
    public FlightSchedule findById(Long scheduleId) {
        return flightScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule with ID not found!"));
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
        flightScheduleRepository.deleteAll();
        return "Deleted all successfully";
    }
}
