package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "maintanance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintanance_id")
    private Long maintenanceId;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id")
    @JsonIgnoreProperties({"airline", "flights", "maintenances"})
    private Aircraft aircraft;

    public Maintenance() {}

    public Maintenance(String description, LocalDateTime date, String type) {
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                ", maintenanceId=" + maintenanceId +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
