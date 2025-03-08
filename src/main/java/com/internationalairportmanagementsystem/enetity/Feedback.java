package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "feedbakc")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;
    @Column(name = "content")
    private String content;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonIgnoreProperties({"baggages", "feedbacks", "checkIns", "userEntity"})
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonIgnoreProperties({"departureAirport"
            , "arrivalAirport", "aircraft",
            "tickets", "checkIns", "feedbacks",
            "baggages", "gateAssignments", "flightSchedules", "cargos", "employees"})
    private Flight flight;


    public Feedback() {}

    public Feedback(String content, String status){
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "content='" + content + '\'' +
                ", feedbackId=" + feedbackId +
                ", status='" + status + '\'' +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }
}
