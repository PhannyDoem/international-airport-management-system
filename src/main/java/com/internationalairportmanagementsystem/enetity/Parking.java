package com.internationalairportmanagementsystem.enetity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_id")
    private Long partId;
    @Column(name = "location")
    private String location;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "rate")
    private Double rate;



    public Parking() {}

    public Parking(String location, Integer capacity, Double rate) {
        this.location = location;
        this.capacity = capacity;
        this.rate = rate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "capacity=" + capacity +
                ", partId=" + partId +
                ", location='" + location + '\'' +
                ", rate=" + rate +
                '}';
    }
}
