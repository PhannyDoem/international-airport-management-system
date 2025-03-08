package com.internationalairportmanagementsystem.enetity;

import jakarta.persistence.*;

@Entity
@Table(name = "rental_services")
public class RentalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "rate")
    private Double rate;

    public RentalService() {}

    public RentalService(String type, String description, Double rate) {
        this.type = type;
        this.description = description;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RentalService{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", rate=" + rate +
                '}';
    }
}
