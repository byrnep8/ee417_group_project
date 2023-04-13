package com.klassycafe.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private final ReservationService resService;
	
	
	public ReservationController(ReservationService resService ) {
		this.resService = resService;
	}
	
	/*
	 * 	Get Mapping to return a list of the reservations already made that correspond
	 * 	To the specified date and timeOfDay
	 */
	@GetMapping("/get/reservations_specified")
	@CrossOrigin("*")
	public ResponseEntity<List<Reservation>> listReservations(){
		
		// return ResponseEntity.ok(this.resService.listReservations());
		return ResponseEntity.ok(this.resService.listReservations());
	}
	/*
	 * 	Get Mapping to return a list of the reservations already made that correspond
	 * 	To the specified date and timeOfDay
	 */
	@GetMapping("/get/reservations")
	@CrossOrigin("*")
	public ResponseEntity<List<Reservation>> listAllReservations(){
		
		// return ResponseEntity.ok(this.resService.listReservations());
		return ResponseEntity.ok(this.resService.listAllReservations());
	}
	
	/*
	 * 	Post Mapping to to update the date, time of day of reservation wanting to retrieve
	 */
	@PostMapping("/post/reservations_info")
	@CrossOrigin("*")
	public ResponseEntity resInfoForGet(@RequestBody @Validated @NonNull Reservation reserve1){
		System.out.println(reserve1);
		this.resService.updateDate(reserve1.getYear(), reserve1.getMonth(), reserve1.getDay());
		this.resService.updateTimeOfDay(reserve1.getTimeOfDay());
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
	}
	
	/*
	 * 	Post Mapping to check the current availabilities at the specified date and time
	 * 	Return a message indicating a reservation made or bookings full
	 */
	@PostMapping("/post/reservations")
	@CrossOrigin("*")
	public ResponseEntity createReservation(@RequestBody @Validated @NonNull Reservation reserve1) {

		// System.out.println(reserve1);
		List<Reservation> resList = resService.listAllReservations();
		// Checking if reservation is present
		if( !resService.checkReservations(resList, reserve1) ) {
			ResponseEntity response = new ResponseEntity<>("Reservation not made, time slot is filled", HttpStatus.BAD_REQUEST);
			// Responding indicating not accepted
			return response;
		}
		// System.out.println("Success");
		// Responding indicating success
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
	}
}
