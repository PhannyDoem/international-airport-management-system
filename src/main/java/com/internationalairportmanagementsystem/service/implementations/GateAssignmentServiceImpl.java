package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostGateAssignmentDto;
import com.internationalairportmanagementsystem.dtos.puts.PutGateAssignmentDto;
import com.internationalairportmanagementsystem.enetity.GateAssignment;
import com.internationalairportmanagementsystem.mappers.GateAssignmentMapper;
import com.internationalairportmanagementsystem.repository.GateAssignmentRepository;
import com.internationalairportmanagementsystem.service.interfaces.GateAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateAssignmentServiceImpl implements GateAssignmentService {
    private GateAssignmentMapper gateAssignmentMapper;
    private GateAssignmentRepository gateAssignmentRepository;

    @Autowired
    public GateAssignmentServiceImpl(GateAssignmentMapper gateAssignmentMapper, GateAssignmentRepository gateAssignmentRepository) {
        this.gateAssignmentMapper = gateAssignmentMapper;
        this.gateAssignmentRepository = gateAssignmentRepository;
    }

    @Override
    public GateAssignment create(PostGateAssignmentDto postGateAssignmentDto) {
        GateAssignment assignment = gateAssignmentMapper.postToAssignment(postGateAssignmentDto);
        return gateAssignmentRepository.save(assignment);
    }

    @Override
    public GateAssignment update(Long assignmentId, PutGateAssignmentDto putGateAssignmentDto) {
        GateAssignment gateAssignment =  gateAssignmentMapper.putToAssignment(putGateAssignmentDto);
        return gateAssignmentRepository.save(gateAssignment);
    }

    @Override
    public GateAssignment findById(Long assignmentId) {
        return gateAssignmentRepository.findById(assignmentId).orElse(null);
    }

    @Override
    public List<GateAssignment> findAll() {
        return gateAssignmentRepository.findAll();
    }

    @Override
    public String deleteById(Long assignmentId) {
        gateAssignmentRepository.deleteById(assignmentId);
        return "Deleted Successfully";
    }

    @Override
    public String deleteAll() {
        gateAssignmentRepository.deleteAll();
        return "Deleted All Successfully";
    }
}
