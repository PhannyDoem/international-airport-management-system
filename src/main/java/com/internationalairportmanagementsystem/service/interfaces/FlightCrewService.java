package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightCrewDto;
import com.internationalairportmanagementsystem.enetity.Flight;

public interface FlightCrewService {
    Flight create(PostFlightCrewDto  postFlightCrewDto);
    void deleteByFlightIdAndEmployeeId(Long flightId, Long employeeId);
}
