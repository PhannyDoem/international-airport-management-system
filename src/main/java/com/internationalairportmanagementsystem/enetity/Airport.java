package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_Id")
    private Long airportId;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "location_city")
    private String locationCity;
    @Column(name = "location_country")
    private String locationCountry;

    @OneToMany(mappedBy = "departureAirport")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks",
    "gateAssignments", "tickets", "flightSchedules", "cargo", "empoyees"})
    private List<Flight> departures;

    @OneToMany(mappedBy = "departureAirport")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns", "baggages", "feedbacks",
            "gateAssignments", "tickets", "flightSchedules", "cargo", "empoyees"})
    private List<Flight> arrivals;


    public Airport() {}

    public Airport(String code, String name, String locationCity, String locationCountry) {
        this.code = code;
        this.name = name;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public List<Flight> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Flight> arrivals) {
        this.arrivals = arrivals;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Flight> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Flight> departures) {
        this.departures = departures;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportId=" + airportId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                '}';
    }

}
