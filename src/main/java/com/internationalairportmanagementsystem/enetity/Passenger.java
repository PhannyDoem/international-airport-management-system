package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerId;
    @Column(name = "name")
    private String name;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "contact_details")
    private String contactDetails;


    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Feedback>  feedbacks;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<CheckIn>  checkIns;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger", "boardingPass"})
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties({"flight", "passenger"})
    private List<Baggage> baggages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_user_id")
    @JsonIgnoreProperties({"role", "passenger", "employee"})
    private UserEntity userEntity;


    public Passenger() {}

    public Passenger(String name, String passportNumber, String nationality, String contactDetails) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.contactDetails = contactDetails;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }

    public List<CheckIn> getCheckIns() {
        return checkIns;
    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "baggages=" + baggages +
                ", passengerId=" + passengerId +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", feedbacks=" + feedbacks +
                ", checkIns=" + checkIns +
                ", tickets=" + tickets +
                ", userEntity=" + userEntity +
                '}';
    }

}
