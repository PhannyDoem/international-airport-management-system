package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostSecurityCheckpointDto;
import com.internationalairportmanagementsystem.dtos.puts.PutSecurityCheckpointDto;
import com.internationalairportmanagementsystem.enetity.SecurityCheckPoint;
import org.springframework.stereotype.Service;

@Service
public class SecurityCheckpointMapper {
    public SecurityCheckPoint postToSecurityCheckpoint(PostSecurityCheckpointDto postSecurityCheckpointDto) {
        SecurityCheckPoint securityCheckPoint = new SecurityCheckPoint(
                postSecurityCheckpointDto.location(),
                postSecurityCheckpointDto.operatingHours()
        );
        securityCheckPoint.setCheckpointId(0L);
        return securityCheckPoint;
    }

    public SecurityCheckPoint putToSecurityCheckpoint(PutSecurityCheckpointDto putSecurityCheckpointDto) {
        SecurityCheckPoint securityCheckPoint = new SecurityCheckPoint(
                putSecurityCheckpointDto.location(),
                putSecurityCheckpointDto.operatingHours()
        );
        securityCheckPoint.setCheckpointId(putSecurityCheckpointDto.checkpointId());
        return securityCheckPoint;
    }

}
