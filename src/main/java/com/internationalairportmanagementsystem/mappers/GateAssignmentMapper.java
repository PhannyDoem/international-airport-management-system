package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostGateAssignmentDto;
import com.internationalairportmanagementsystem.dtos.puts.PutGateAssignmentDto;
import com.internationalairportmanagementsystem.enetity.Flight;
import com.internationalairportmanagementsystem.enetity.GateAssignment;
import org.springframework.stereotype.Service;

@Service
public class GateAssignmentMapper {
    public GateAssignment postToAssignment(PostGateAssignmentDto postGateAssignmentDto) {
        GateAssignment gateAssignment = new GateAssignment(
                postGateAssignmentDto.gate(),
                postGateAssignmentDto.assignmentTime()
        );
        gateAssignment.setAssignmentId(0L);
        if (postGateAssignmentDto.flightId() != null) {
            Flight flight = new Flight();
            flight.setFlightId(postGateAssignmentDto.flightId());
            gateAssignment.setFlight(flight);
        }
        return gateAssignment;
    }

    public GateAssignment putToAssignment(PutGateAssignmentDto putGateAssignmentDto) {
        GateAssignment gateAssignment = new GateAssignment(
                putGateAssignmentDto.gate(),
                putGateAssignmentDto.assignmentTime()
        );
        gateAssignment.setAssignmentId(putGateAssignmentDto.assignmentId());

        if(putGateAssignmentDto.flightId() != null) {
            Flight flight = new Flight();
            flight.setFlightId(putGateAssignmentDto.flightId());
            gateAssignment.setFlight(flight);
        }
        return gateAssignment;
    }
}
