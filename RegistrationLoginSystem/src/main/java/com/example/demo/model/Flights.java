package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing flight details.
 */
@Entity
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the Flights entity

    private String departureCity;
    private String arrivalCity;
    private String travelDate;

    // Default constructor
    public Flights() {
        super();
    }

    // Parameterized constructor
    public Flights(String departureCity, String arrivalCity, String travelDate) {
        super();
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.travelDate = travelDate;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    @Override
    public String toString() {
        return "Flights [id=" + id + ", departureCity=" + departureCity + ", arrivalCity=" + arrivalCity 
               + ", travelDate=" + travelDate + "]";
    }
}
