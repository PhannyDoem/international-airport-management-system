package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostSecurityCheckpointDto;
import com.internationalairportmanagementsystem.dtos.puts.PutSecurityCheckpointDto;
import com.internationalairportmanagementsystem.enetity.SecurityCheckPoint;
import com.internationalairportmanagementsystem.mappers.SecurityCheckpointMapper;
import com.internationalairportmanagementsystem.repository.SecurityCheckpointRepository;
import com.internationalairportmanagementsystem.service.interfaces.SecurityCheckpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityCheckpointServiceImpl implements SecurityCheckpointService {
    private SecurityCheckpointRepository securityCheckpointRepository;
    private SecurityCheckpointMapper securityCheckpointMapper;

    @Autowired
    public SecurityCheckpointServiceImpl(
            SecurityCheckpointRepository securityCheckpointRepository,
            SecurityCheckpointMapper securityCheckpointMapper
    ){
        this.securityCheckpointRepository = securityCheckpointRepository;
        this.securityCheckpointMapper = securityCheckpointMapper;
    }

    @Override
    public SecurityCheckPoint create(PostSecurityCheckpointDto postSecurityCheckpointDto) {
        SecurityCheckPoint securityCheckPoint = securityCheckpointMapper
                .postToSecurityCheckpoint(postSecurityCheckpointDto);
        return securityCheckpointRepository.save(securityCheckPoint);
    }

    @Override
    public SecurityCheckPoint update(PutSecurityCheckpointDto putSecurityCheckpointDto) {
        SecurityCheckPoint securityCheckPoint = securityCheckpointMapper
                .putToSecurityCheckpoint(putSecurityCheckpointDto);
        return securityCheckpointRepository.save(securityCheckPoint);
    }

    @Override
    public SecurityCheckPoint findById(Long securityCheckPointId) {
        return securityCheckpointRepository.findById(securityCheckPointId).orElse(null);
    }

    @Override
    public List<SecurityCheckPoint> findAll() {
        return securityCheckpointRepository.findAll();
    }

    @Override
    public String deleteById(Long securityCheckPointId) {
        securityCheckpointRepository.deleteById(securityCheckPointId);
        return "Deleted security check point";
    }

    @Override
    public String deleteAll() {
        securityCheckpointRepository.deleteAll();
        return "Deleted security check points";
    }
}
