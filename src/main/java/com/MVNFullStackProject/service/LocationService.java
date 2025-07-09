package com.MVNFullStackProject.service;

import com.MVNFullStackProject.entity.Location;

import java.util.List;

public interface LocationService {
	List<Location> getLocations(String areaName, String serviceType);
	String checkServiceAvailability(String areaName);
}
