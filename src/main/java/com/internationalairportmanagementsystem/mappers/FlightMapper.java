package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightDto;
import com.internationalairportmanagementsystem.dtos.puts.PutFlightDto;
import com.internationalairportmanagementsystem.enetity.Aircraft;
import com.internationalairportmanagementsystem.enetity.Airport;
import com.internationalairportmanagementsystem.enetity.Employee;
import com.internationalairportmanagementsystem.enetity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {
    public Flight postToFlight(PostFlightDto  postFlightDto) {
        Flight flight = new Flight(
                postFlightDto.flightNumber(),
                postFlightDto.departureTime(),
                postFlightDto.arrivalTime()
        );
        flight.setFlightId(0L);
        if (postFlightDto.departureAirportId() != null) {
            Airport airport = new Airport();
            airport.setAirportId(postFlightDto.departureAirportId());
            flight.setDepartureAirport(airport);
        }
        if (postFlightDto.arrivalAirportId() != null) {
            Airport airport = new Airport();
            airport.setAirportId(postFlightDto.arrivalAirportId());
            flight.setArrivalAirport(airport);
        }
        if (postFlightDto.aircraftId() != null) {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(postFlightDto.aircraftId());
            flight.setAircraft(aircraft);
        }
        if (postFlightDto.employeeIds() != null && !postFlightDto.employeeIds().isEmpty()) {
            for (Long employeeId : postFlightDto.employeeIds()) {
                Employee employee = new Employee();
                employee.setEmployeeId(employeeId);
                flight.addEmployee(employee);
            }
        }
        return flight;
    }

    public Flight putToFlight(PutFlightDto putFlightDto) {
        Flight flight = new Flight(
                putFlightDto.flightNumber(),
                putFlightDto.departureTime(),
                putFlightDto.arrivalTime()
        );
        flight.setFlightId(putFlightDto.flightId());
        if (putFlightDto.departureAirportId() != null) {
            Airport airport = new Airport();
            airport.setAirportId(putFlightDto.departureAirportId());
            flight.setDepartureAirport(airport);
        }
        if (putFlightDto.arrivalAirportId() != null) {
            Airport airport = new Airport();
            airport.setAirportId(putFlightDto.arrivalAirportId());
            flight.setArrivalAirport(airport);
        }
        if (putFlightDto.aircraftId() != null) {
            Aircraft aircraft = new Aircraft();
            aircraft.setAircraftId(putFlightDto.aircraftId());
            flight.setAircraft(aircraft);
        }
        if (putFlightDto.employeeIds() != null && !putFlightDto.employeeIds().isEmpty()) {
            for (Integer employeeId : putFlightDto.employeeIds()){
                Employee employee = new Employee();
                employee.setEmployeeId(Long.valueOf(employeeId));
                flight.addEmployee(employee);
            }
        }
        return flight;
    }
}
