package com.internationalairportmanagementsystem.enetity;


import jakarta.persistence.*;

@Entity
@Table(name = "security_checkpoints")
public class SecurityCheckPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checkpoint_id")
    private Long checkpointId;
    @Column(name = "lacation")
    private String location;
    @Column(name = "operating_hours")
    private String operatingHours;

    public SecurityCheckPoint() {}
    public SecurityCheckPoint( String location, String operatingHours) {
        this.location = location;
        this.operatingHours = operatingHours;
    }

    public Long getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Long checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Override
    public String toString() {
        return "SecurityCheckPoint{" +
                "checkpointId=" + checkpointId +
                ", location='" + location + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
