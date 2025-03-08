package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "baggage")
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baggage_id")
    private Long baggageId;
    @Column(name = "wright")
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport", "arrivalAirport", "aircraft", "checkIns",
            "baggages", "feedbacks", "gateAssignments", "tickets", "flightSchedules", "cargo", "employees"})
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"baggages", "feedbacks", "checkIns", "tickets", "userEntity"})
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    public Baggage() {}

    public Baggage(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public Long getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(Long baggageId) {
        this.baggageId = baggageId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "baggageId=" + baggageId +
                ", weight=" + weight +
                '}';
    }
}
