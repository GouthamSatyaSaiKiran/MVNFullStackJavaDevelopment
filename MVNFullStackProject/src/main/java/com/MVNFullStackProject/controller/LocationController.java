package com.MVNFullStackProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MVNFullStackProject.entity.Location;
import com.MVNFullStackProject.repository.LocationRepository;
import com.MVNFullStackProject.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private LocationRepository locationRepository;

	@PostMapping
	public ResponseEntity<String> addLocation(@RequestBody Location location) {
	    locationRepository.save(location);
	    return ResponseEntity.ok("Location added successfully");
	}

	@GetMapping("/areaName/{areaName}/serviceType/{serviceType}")
	public ResponseEntity<?> getLocations(@PathVariable String areaName, @PathVariable String serviceType) {
	    List<Location> locations = locationService.getLocations(areaName, serviceType);

	    if (locations.isEmpty()) {
	    	String message = "No " + serviceType + " service found in " + areaName + ".";
	        return ResponseEntity.ok(message);	        
	    }

	    return ResponseEntity.ok(locations);
	}

	@GetMapping("/check")
	public ResponseEntity<String> checkAvailability(@RequestParam String area) {
	    return ResponseEntity.ok(locationService.checkServiceAvailability(area));
	}
}