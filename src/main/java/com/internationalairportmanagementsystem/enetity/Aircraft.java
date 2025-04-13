package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Long aircraftId;
    @Column(name = "tail_number")
    private String  tailNumber;
    @Column(name = "model")
    private String model;
    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    @JsonIgnoreProperties({"aircrafts"})
    private Airline airline;

    @OneToMany(mappedBy = "aircraft")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns",
            "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargos", "employees"})
    private List<Flight> flights;


    @OneToMany(mappedBy = "aircraft")
    @JsonIgnoreProperties({"aircraft"})
    private List<Maintenance>  maintenances;



    public  Aircraft() {}

    public Aircraft(String tailNumber, String model, Integer capacity, Airline airline) {
        this.tailNumber = tailNumber;
        this.model = model;
        this.capacity = capacity;
        this.airline = airline;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "aircraftId=" + aircraftId +
                ", tailNumber='" + tailNumber + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
