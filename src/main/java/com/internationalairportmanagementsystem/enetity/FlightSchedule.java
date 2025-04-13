package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Column(name = "scheduled_departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime scheduledDepartureTime;
    @Column(name = "scheduled_arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime scheduledArrivalTime;
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft",
            "checkIns", "baggages", "feedbacks", "gateAssignments", "tickets",
            "flightSchedules", "cargos", "employees"})
    private Flight flight;


    public FlightSchedule() {}

    public FlightSchedule(LocalDateTime scheduledDepartureTime, LocalDateTime scheduledArrivalTime, String status, Flight flight) {
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.status = status;
        this.flight = flight;

    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public void setScheduledArrivalTime(LocalDateTime scheduledArrivalTime) {
        this.scheduledArrivalTime = scheduledArrivalTime;
    }

    public LocalDateTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }

    public void setScheduledDepartureTime(LocalDateTime scheduledDepartureTime) {
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                ", scheduleId=" + scheduleId +
                ", scheduledDepartureTime=" + scheduledDepartureTime +
                ", scheduledArrivalTime=" + scheduledArrivalTime +
                ", status='" + status + '\'' +
                '}';
    }
}
