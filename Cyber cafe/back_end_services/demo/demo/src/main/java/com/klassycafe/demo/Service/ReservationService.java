/*
 * 	Author: Patrick Byrne
 * 
 * 	Business Logic of Reservation Controller
 */

package com.klassycafe.demo.Service;

import java.util.List;
import java.util.Vector;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.klassycafe.demo.Entity.Reservation;

@Service
public class ReservationService {

	private static Integer maxSeating = 10;
	// Date is deprecated but still works, year starts at 1900
	Date date = new Date(123, 4, 5);
	
	List<Reservation> demo = List.of(new Reservation("Patrick", "Byrne", "patb@gmail.com", "Breakfast", 4, 2023, 4, 5), 
			new Reservation("Kevin", "Connaughton", "kevc@gmail.com", "Breakfast", 2, 2023, 4, 5),
			new Reservation("Arun", "Rathnasami", "arun@gmail.com", "Breakfast", 1, 2023, 4, 5));
	
	public ReservationService() {
	}
	
	// Placeholder for function to get list of reservations from database
	public List<Reservation> listReservations() {
		// TODO Auto-generated method stub
		return demo;
	}
	
	// Placeholder for filtering of list by time day
	public List<Reservation> filterReservations(List<Reservation> reserve, String timeOfDay){
		return reserve;
	}
	
	public void createReservation(Reservation reserve) {
		// Add reservation to database, local list for now
		// demo.add(reserve);
		
	}
	
	// Function to check if the reservation already exists
	public boolean checkReservations(List<Reservation> res_list, Reservation reserve) {
		// List<Reservation> tmp_list = new Vector<Reservation>();
		Integer guestNum = 0;
		
		for( int i=0; i < res_list.size(); i++ ) {
			 Reservation tmp = res_list.get(i);
			 
				if( tmp.getTimeOfDay().equalsIgnoreCase(reserve.getTimeOfDay()) &&
						tmp.getDay() == reserve.getDay() &&
						tmp.getMonth() == reserve.getMonth() &&
						tmp.getYear() == reserve.getYear()) {
					// tmp_list.add(tmp);
					guestNum = guestNum + tmp.getNumberPeople();
				}
		}
		
		// If guests already booked plus guests with new booking less than max seating create booking
		if( guestNum + reserve.getNumberPeople() <=  this.maxSeating ) {
			this.createReservation(reserve);
			return true;
		}
		return true;
	}
}
