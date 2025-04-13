package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostGateAssignmentDto;
import com.internationalairportmanagementsystem.dtos.puts.PutGateAssignmentDto;
import com.internationalairportmanagementsystem.enetity.GateAssignment;

import java.util.List;

public interface GateAssignmentService {
    GateAssignment create(PostGateAssignmentDto postGateAssignmentDto);
    GateAssignment update(Long assignmentId,PutGateAssignmentDto putGateAssignmentDto);
    GateAssignment findById(Long assignmentId);
    List<GateAssignment> findAll();
    String deleteById(Long assignmentId);
    String deleteAll();
}
