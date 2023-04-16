package com.klassycafe.demo.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * 	Author: Patrick Byrne
 * 
 * 	Class to define the entity of a reservation
 * 	Class states for date, number of people for the reservation
 * 
 */
import jakarta.persistence.*;

@Entity
@Table(name="customers")
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String timeOfDay;
	private String firstName;
	private String lastName;
	private String email;
	private String message;
	private String phoneNum;
	private Integer numberPeople;
	private Integer month, year, day;
	
	// Constructor
	public Reservation(String firstName, String lastName, String email, String timeOfDay, String phoneNum, Integer numberPeople, Integer year, Integer month, Integer day) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeOfDay = timeOfDay;
		this.numberPeople = numberPeople;
		this.year = year;
		this.month = month;
		this.day = day;
		this.phoneNum = phoneNum;
	}
	
	public Reservation() {
		this.phoneNum = "";
		this.numberPeople = 0;
		this.email = "";
		this.firstName = "";
		this.lastName = "";
	}
    
    @Override
    public String toString() {
    	String formatted_out = "{\"firstName\": \""+this.firstName+
    			"\",\"lastName\": \""+this.lastName+
    			"\",\"numberPeople\": \""+this.numberPeople.toString()+
    			"\",\"email\": \""+this.email+
    			"\",\"PhoneNum\": \""+this.phoneNum+
    			"\",\"message\": \""+this.message+
    			"\",\"Year\": \""+this.year.toString()+
    			"\",\"Month\": \""+this.month.toString()+
    			"\",\"Day\": \""+this.day.toString()+
    			"\"}";
    	return formatted_out;
    }
}
