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
                postGateAssignmentDto.assignedTime(),
                postGateAssignmentDto.flight()

        );
        gateAssignment.setAssignmentId(0L);
        return gateAssignment;
    }

    public GateAssignment putToAssignment(PutGateAssignmentDto putGateAssignmentDto) {
        GateAssignment gateAssignment = new GateAssignment(
                putGateAssignmentDto.gate(),
                putGateAssignmentDto.assignedTime(),
                putGateAssignmentDto.flight()
        );
        gateAssignment.setAssignmentId(0L);
        return gateAssignment;
    }
}
