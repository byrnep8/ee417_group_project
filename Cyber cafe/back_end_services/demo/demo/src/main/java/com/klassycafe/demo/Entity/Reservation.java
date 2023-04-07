package com.klassycafe.demo.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * 	Author: Patrick Byrne
 * 
 * 	Class to define the entity of a reservation
 * 	Class states for date, number of people for the reservation
 * 
 */
// import jakarta.persistence.*;

// @Entity(name="reservation")
public class Reservation {
	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String timeOfDay;
	private String firstName, lastName;
	private String email;
	private Integer numberPeople;
	private Integer month, year, day;
	
	// Constructor
	public Reservation() {
		
	}
	
	// Constructor
	public Reservation(String firstName, String lastName, String email, String timeOfDay, Integer numberPeople, Integer year, Integer month, Integer day) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeOfDay = timeOfDay;
		this.numberPeople = numberPeople;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// Getters for the class properties
	@JsonIgnore
	 public Long getId() {
        return this.id;
    }

    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public Integer getNumberPeople() {
        return this.numberPeople;
    }
    public Integer getYear() {
    	return this.year;
    }
    public Integer getMonth() {
    	return this.month;
    }
    
    public Integer getDay() {
    	return this.day;
    }

    // Setters
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public void setLastName(String name) {
        this.lastName = name;
    }
    
    public void setMonth(Integer month) {
    	this.month = month;
    }
    public void setYear(Integer year) {
    	this.year = year;
    }
    public void setDay(Integer day) {
    	this.day = day;
    }


    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }
}
