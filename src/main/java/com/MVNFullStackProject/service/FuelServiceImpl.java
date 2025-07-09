package com.MVNFullStackProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MVNFullStackProject.entity.FuelBooking;
import com.MVNFullStackProject.repository.FuelBookingRepository;

@Service
public class FuelServiceImpl implements FuelService {

    @Autowired
    private FuelBookingRepository fuelBookingRepository;

    @Override
    public String book(FuelBooking booking) {
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("Pending"); // Default status
        fuelBookingRepository.save(booking);
        return "Fuel booking confirmed for " + booking.getFuelType();
    }

    @Override
    public List<FuelBooking> getBookings(Long userId) {
        return fuelBookingRepository.findByUserId(userId);
    }
}
