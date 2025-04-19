package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostFlightCrewDto;
import com.internationalairportmanagementsystem.enetity.Flight;

public interface FlightCrewService {
    Flight create(PostFlightCrewDto  postFlightCrewDto);
    Flight deleteByFlightIdAndEmployeeId(Long flightId, Long employeeId);
}
