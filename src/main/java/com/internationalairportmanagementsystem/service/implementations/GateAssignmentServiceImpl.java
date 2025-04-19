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
import java.util.Objects;

@Service
public class GateAssignmentServiceImpl implements GateAssignmentService {
    private final GateAssignmentMapper gateAssignmentMapper;
    private final GateAssignmentRepository gateAssignmentRepository;

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
        if (assignmentId != null){
            gateAssignmentRepository.findById(assignmentId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setGate(putGateAssignmentDto.gate());
                                updated.setFlight(putGateAssignmentDto.flight());
                                updated.setAssignedTime(putGateAssignmentDto.assignedTime());
                                GateAssignment assignment = gateAssignmentRepository.save(updated);
                                return gateAssignmentRepository.save(assignment);
                            }
                    );
        }
        return gateAssignmentRepository.findById(Objects.requireNonNull(assignmentId))
                .orElseThrow(()-> new RuntimeException("Update assignment not found"));
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
