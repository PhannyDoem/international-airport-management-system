package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostSecurityCheckpointDto;
import com.internationalairportmanagementsystem.dtos.puts.PutSecurityCheckpointDto;
import com.internationalairportmanagementsystem.enetity.SecurityCheckPoint;

import java.util.List;

public interface SecurityCheckpointService {
    SecurityCheckPoint create(PostSecurityCheckpointDto  postSecurityCheckpointDto);
    SecurityCheckPoint update(PutSecurityCheckpointDto putSecurityCheckpointDto);
    SecurityCheckPoint findById(Long securityCheckPointId);
    List<SecurityCheckPoint> findAll();
    String deleteById(Long securityCheckPointId);
    String deleteAll();
}
