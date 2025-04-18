package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;
    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id") // Correct for departure
    @JsonIgnoreProperties({"departure", "arrivals"})
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id") // Fixed: Unique column for arrival
    @JsonIgnoreProperties({"departure", "arrivals"})
    private Airport arrivalAirport;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id") // Fixed typo: "aircracft_id" → "aircraft_id"
    @JsonIgnoreProperties({"airline", "flight", "maintenances"})
    private Aircraft aircraft;



    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<CheckIn> checkIns;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Baggage> baggages;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Feedback> feedbacks;

    @OneToOne(mappedBy = "flight", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"flight"})
    private GateAssignment  gateAssignment;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight", "passenger", "boardingPass"})
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight"})
    private List<FlightSchedule> flightSchedules;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight"})
    private List<Cargo> cargos;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "flight_crews",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnoreProperties({"flight", "userEntity"})
    private List<Employee> employees;


   public Flight(){}

    public Flight(String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime,Airport arrivalAirport, GateAssignment gateAssignment){
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.arrivalAirport = arrivalAirport;
        this.gateAssignment = gateAssignment;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }

    public List<CheckIn> getCheckIn() {
        return checkIns;
    }

    public void setCheckIn(List<CheckIn> checkIn) {
        this.checkIns = checkIn;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<FlightSchedule> getFlightSchedules() {
        return flightSchedules;
    }

    public void setFlightSchedules(List<FlightSchedule> flightSchedules) {
        this.flightSchedules = flightSchedules;
    }

    public GateAssignment getGateAssignment() {
        return gateAssignment;
    }

    public void setGateAssignment(GateAssignment gateAssignment) {
        this.gateAssignment = gateAssignment;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Flight{" +

                ", flightId=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
