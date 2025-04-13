package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "gate_assignments")
public class GateAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assingment_id")
    private Long assignmentId;
    @Column(name = "gate")
    private String gate;
    @Column(name = "assignement_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignedTime;

    @OneToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft",
            "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets",
            "flightSchedules", "cargos", "employees"})
    private Flight flight;


    public GateAssignment() {}

    public GateAssignment(String gate, LocalDateTime assignedTime, Flight flight) {
        this.gate = gate;
        this.assignedTime = assignedTime;
        this.flight = flight;
    }

    public LocalDateTime getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(LocalDateTime assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return "GateAssignment{" +
                "assignedTime=" + assignedTime +
                ", assignmentId=" + assignmentId +
                ", gate='" + gate + '\'' +
                '}';
    }
}
