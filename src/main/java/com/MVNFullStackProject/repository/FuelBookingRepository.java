package com.MVNFullStackProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MVNFullStackProject.entity.FuelBooking;

public interface FuelBookingRepository extends JpaRepository<FuelBooking, Long> {
}
