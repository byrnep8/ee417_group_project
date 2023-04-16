/*
 * 	Author: Patrick Byrne
 * 
 * 	Business Logic of Reservation Controller
 */

package com.klassycafe.demo.Service;

import java.util.List;
import java.util.Vector;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klassycafe.demo.Entity.Reservation;
import com.klassycafe.demo.Repository.ReservationRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Service
public class ReservationService {

	private static Integer maxSeating = 10;
	private static int year = 0;
	private static int month = 0;
	private static int day = 0;
	private static String timeOfDay = "";
	
	@Autowired
	private final ReservationRepository resRepo;
	
	@Autowired
	public ReservationService(ReservationRepository resRepo) {
		this.resRepo = resRepo;
	}
	
	public List<Reservation> listReservations() {
		List<Reservation> res_list = new Vector();
		List<Reservation> tmp_list = new Vector();
		res_list = this.listAllReservations();
		if (year == 0 && month ==0 && this.day == 0) {
			if(timeOfDay != "") {
				tmp_list = this.filterReservationsTimeOfDay(res_list, timeOfDay);
			}
		}
		else {
			tmp_list=  this.filterReservationsDate(res_list, year, month, day);
			if(timeOfDay != "") {
				tmp_list = this.filterReservationsTimeOfDay(tmp_list, timeOfDay);
			}
		}
		return tmp_list;
	}
	// Placeholder for function to get list of reservations from database
	public List<Reservation> listAllReservations() {
		// TODO Auto-generated method stub
		return resRepo.findAll();
	}
	
	public static void updateDate(int year1, int month1, int day1) {
		year = year1;
		month = month1;
		day = day1;
	}
	
	public static void updateTimeOfDay(String timeOfDay1) {
		timeOfDay = timeOfDay1;
	}
	
	// Function for filtering of list by time day
	public List<Reservation> filterReservationsTimeOfDay(List<Reservation> reserve, String timeOfDay){
		List<Reservation> res_list = new Vector();
		for ( Reservation tmp : reserve ) {
			// If time of day matches append list
			if ( tmp.getTimeOfDay().equalsIgnoreCase(timeOfDay) ) {
				res_list.add(tmp);
			}
		}
		return res_list;
	}
	
	// Function for filtering list according to date
	public List<Reservation> filterReservationsDate(List<Reservation> reserve, int year, int month, int day){
		List<Reservation> res_list = new Vector();
		for ( Reservation tmp : reserve ) {
			// If dates match append the list
			if ( tmp.getYear() == year && tmp.getMonth() == month && tmp.getDay() == day ) {
				res_list.add(tmp);
			}
		}
		return res_list;
	}
	
	/*
	 * Saving reservation to the database
	 * Use after validating it's use
	 */
	public void createReservation(Reservation reserve) {
		// Add reservation to database, local list for now
		// demo.add(reserve);
		this.resRepo.save(reserve);
	}
	
	// Function to check if the reservation already exists
	public boolean checkReservations(List<Reservation> res_list, Reservation reserve) {
		// List<Reservation> tmp_list = new Vector<Reservation>();
		Integer guestNum = 0;
		
		for( int i=0; i < res_list.size(); i++ ) {
			 Reservation tmp = res_list.get(i);
			 
				if( tmp.getTimeOfDay().equalsIgnoreCase(reserve.getTimeOfDay()) &&
						tmp.getDay().intValue() == reserve.getDay().intValue() &&
						tmp.getMonth().intValue() == reserve.getMonth().intValue() &&
						tmp.getYear().intValue() == reserve.getYear().intValue()) {
					// tmp_list.add(tmp);
					guestNum = guestNum + tmp.getNumberPeople();
				}
		}
		
		// If guests already booked plus guests with new booking less than max seating create booking
		if( guestNum.intValue() + reserve.getNumberPeople().intValue() <=  this.maxSeating ) {
			this.createReservation(reserve);
			return true;
		}
		return false;
	}
}
