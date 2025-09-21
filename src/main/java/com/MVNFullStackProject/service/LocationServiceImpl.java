package com.MVNFullStackProject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MVNFullStackProject.entity.Location;
import com.MVNFullStackProject.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getLocations(String areaName, String serviceType) {
        return locationRepository.findByAreaNameAndServiceType(areaName, serviceType);
    }

    @Override
    public String checkServiceAvailability(String areaName) {
        boolean available = locationRepository.existsByAreaNameIgnoreCase(areaName);
        return available ? "Service is available in " + areaName : "No service in " + areaName;
    }
}
