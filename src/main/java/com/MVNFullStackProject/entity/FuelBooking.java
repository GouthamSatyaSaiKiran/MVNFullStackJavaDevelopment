package com.MVNFullStackProject.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "fuel_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuelBooking {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Long userId;

	    private String fuelType;

	    private double quantity;

	    private LocalDateTime bookingTime;

	    private String status;

	    private Long branchId;

	    private String vehicleNumber;
}
