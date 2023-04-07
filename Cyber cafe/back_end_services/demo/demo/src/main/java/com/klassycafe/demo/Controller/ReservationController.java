package com.klassycafe.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.klassycafe.demo.Entity.Reservation;
import com.klassycafe.demo.Service.ReservationService;

import io.micrometer.common.lang.NonNull;

@RestController()
public class ReservationController {

	private final ReservationService resService;
	
	public ReservationController(ReservationService resService ) {
		this.resService = resService;
	}
	
	/*
	 * 	Get Mapping to return a list of the reservations already made
	 * 
	 */
	@GetMapping("/get/reservations")
	@CrossOrigin("*")
	public ResponseEntity<List<Reservation>> listReservations(){
		return ResponseEntity.ok(this.resService.listReservations());
	}
	
	/*
	 * 	Get Mapping to return a list of the reservations already made
	 */
//	@GetMapping("/get/reservations")
//	@CrossOrigin("*")
//	public List<Reservation> listReservations(){
//		return resService.listReservations();
//	}
	
	/*
	 * 	Post Mapping to check the current availabilities at the specified date and time
	 * 	Return a message indicating a reservation made or bookings full
	 */
	@PostMapping("/post/reservations")
	@CrossOrigin("*")
	public ResponseEntity createReservation(@RequestBody @Validated @NonNull Reservation reserve) {
		System.out.println("Received Post request");
		List<Reservation> resList = resService.listReservations();
		
		// Checking if reservation is present
		if( !resService.checkReservations(resList, reserve) ) {
			ResponseEntity response = new ResponseEntity<>("Reservation not made, time slot is filled", HttpStatus.BAD_REQUEST);
			// Responding indicating not accepted
			return response;
		}
		
		ResponseEntity response = new ResponseEntity<>("Reservation made", HttpStatus.ACCEPTED);
		// Responding indicating success
		return response;
	}
}
