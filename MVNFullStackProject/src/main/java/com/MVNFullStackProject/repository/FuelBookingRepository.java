package com.MVNFullStackProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MVNFullStackProject.entity.FuelBooking;

public interface FuelBookingRepository extends JpaRepository<FuelBooking, Long> {
    List<FuelBooking> findByUserId(Long userId);
}
