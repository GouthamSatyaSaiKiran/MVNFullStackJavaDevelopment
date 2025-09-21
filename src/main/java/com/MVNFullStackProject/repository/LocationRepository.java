package com.MVNFullStackProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MVNFullStackProject.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	boolean existsByAreaNameIgnoreCase(String areaName);
	List<Location> findByAreaNameAndServiceType(String areaName, String serviceType);
}
