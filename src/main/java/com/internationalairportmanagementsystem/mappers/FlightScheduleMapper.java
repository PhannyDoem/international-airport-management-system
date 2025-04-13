package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightScheduleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightScheduleDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.FlightSchedule;
import org.springframework.stereotype.Service;

@Service
public class FlightScheduleMapper {
   public FlightSchedule postToSchedule(PostFlightScheduleDto  postFlightScheduleDto) {
       FlightSchedule  flightSchedule = new FlightSchedule(
               postFlightScheduleDto.scheduledDepartureTime(),
               postFlightScheduleDto.scheduledArrivalTime(),
               postFlightScheduleDto.status(),
               postFlightScheduleDto.flight()
       );
       flightSchedule.setScheduleId(0L);
       return flightSchedule;
   }

   public FlightSchedule putToSchedule(PutFlightScheduleDto putFlightScheduleDto) {
       FlightSchedule flightSchedule = new FlightSchedule(
               putFlightScheduleDto.scheduledDepartureTime(),
               putFlightScheduleDto.scheduledArrivalTime(),
               putFlightScheduleDto.status(),
               putFlightScheduleDto.flight()
       );
       flightSchedule.setScheduleId(0L);
       return flightSchedule;
   }
}
