package com.MVNFullStackProject.service;

import java.util.List;

import com.MVNFullStackProject.entity.FuelBooking;

public interface FuelService {
    String book(FuelBooking booking);
    List<FuelBooking> getBookings(Long userId);
}
