package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "boarding_pass")
public class BoardingPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boarding_pass_id")
    private Long boardingPassId;
    @Column(name = "gate")
    private String gate;

    @Column(name = "boarding_time", nullable = false)
    private LocalDateTime boardingTime;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    @JsonIgnoreProperties({"flight", "passenger", "boardingPass"})
    private Ticket ticket;

    // Constructors

    public BoardingPass() {
        // Default constructor
    }

    public BoardingPass( String gate, LocalDateTime boardingTime, Ticket ticket) {
        this.gate = gate;
        this.boardingTime = boardingTime;
        this.ticket = ticket;
    }

    public Long getBoardingPassId() {
        return boardingPassId;
    }

    public void setBoardingPassId(Long boardingPassId) {
        this.boardingPassId = boardingPassId;
    }

    public LocalDateTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "BoardingPass{" +
                "boardingPassId=" + boardingPassId +
                ", gate='" + gate + '\'' +
                ", boardingTime=" + boardingTime +
                '}';
    }
}
