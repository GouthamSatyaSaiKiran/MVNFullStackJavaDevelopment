package com.MVNFullStackProject.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MVNFullStackProject.service.FuelService;
import com.MVNFullStackProject.entity.FuelBooking;

@RestController
@RequestMapping("/fuel")
	public class FuelController {
	
@Autowired
	private FuelService fuelService;

@GetMapping("/types")
	public List<String> getFuelTypes() {
	    return Arrays.asList("Petrol", "Diesel", "EV Charging");
	}

@PostMapping("/book")
	public ResponseEntity<String> bookFuel(@RequestBody FuelBooking booking) {
	    return ResponseEntity.ok(fuelService.book(booking));
	}

@GetMapping("/bookings/{userId}")
	public List<FuelBooking> getBookings(@PathVariable Long userId) {
	     return fuelService.getBookings(userId);
	}
	
}


