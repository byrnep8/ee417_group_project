package com.klassycafe.demo.Entity;

import java.util.Date;

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
	private String name;
	private String email;
	private Integer numberPeople;
	private Integer month, year, day;
	
	// Constructor
	public Reservation() {
		
	}
	
	// Constructor
	public Reservation(String name, String email, String timeOfDay, Integer numberPeople, Date date) {
		this.email = email;
		this.name = name;
		this.timeOfDay = timeOfDay;
		this.numberPeople = numberPeople;
		this.year = date.getYear()+1900;
		this.month = date.getMonth();
		this.day = date.getDay();
	}
	
	// Getters for the class properties
	 public Long getId() {
        return this.id;
    }

    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    public String getName() {
        return this.name;
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
    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setName(String name) {
        this.name = name;
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
