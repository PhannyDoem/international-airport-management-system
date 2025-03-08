package com.internationalairportmanagementsystem.enetity;


import jakarta.persistence.*;

@Entity
@Table(name = "airport_services")
public class AirportService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Long serviceId;
    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "operating_hours")
    private String operatingHours;

    public AirportService() {}

    public AirportService( String name, String location, String operatingHours) {
        this.name = name;
        this.location = location;
        this.operatingHours = operatingHours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "AirportService{" +
                "location='" + location + '\'' +
                ", serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
