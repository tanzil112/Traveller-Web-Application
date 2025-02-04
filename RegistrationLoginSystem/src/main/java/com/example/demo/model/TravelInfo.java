package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class TravelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private String travelTips;
    private String visaRequirements;
    private String safetyAlerts;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTravelTips() {
        return travelTips;
    }

    public void setTravelTips(String travelTips) {
        this.travelTips = travelTips;
    }

    public String getVisaRequirements() {
        return visaRequirements;
    }

    public void setVisaRequirements(String visaRequirements) {
        this.visaRequirements = visaRequirements;
    }

    public String getSafetyAlerts() {
        return safetyAlerts;
    }

    public void setSafetyAlerts(String safetyAlerts) {
        this.safetyAlerts = safetyAlerts;
    }
}
