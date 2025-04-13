package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airlines", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Long airlineId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "airline")
    @JsonIgnoreProperties({"airline", "flights", "maintenances"})
    private List<Aircraft>  aircrafts;


    public Airline() {}

    public Airline(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                ", airlineId=" + airlineId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
