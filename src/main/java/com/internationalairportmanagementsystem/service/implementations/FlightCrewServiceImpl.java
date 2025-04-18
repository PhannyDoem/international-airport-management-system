package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightCrewDto;
import com.internationalairportmanagementsystem.enetity.Employee;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.repository.FlightRepository;
import com.internationalairportmanagementsystem.service.interfaces.EmployeeService;
import com.internationalairportmanagementsystem.service.interfaces.FlightCrewService;
import com.internationalairportmanagementsystem.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightCrewServiceImpl implements FlightCrewService {

    private final FlightRepository flightRepository;
    private final FlightService flightService;


    private final EmployeeService employeeService;

    @Autowired
    public FlightCrewServiceImpl(FlightRepository flightRepository, FlightService flightService, EmployeeService employeeService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
        this.employeeService = employeeService;
    }


    @Override
    public Flight create(PostFlightCrewDto postFlightCrewDto) {
        Flight flight = flightService.findById(postFlightCrewDto.flightId());
        Employee employee = employeeService.findById(postFlightCrewDto.employeeId());
        return flightRepository.save(flight);
    }

    @Override
    public Flight deleteByFlightIdAndEmployeeId(Long flightId, Long employeeId) {
        Flight flight = flightService.findById(flightId);
        Employee employee = employeeService.findById(employeeId);
        flight.getEmployees().remove(employee);
       return flightRepository.save(flight);
    }
}
