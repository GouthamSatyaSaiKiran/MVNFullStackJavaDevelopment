package com.MVNFullStackProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MVNFullStackProject.entity.MechanicRequest;

public interface MechanicRequestRepository extends JpaRepository<MechanicRequest, Long> {
}
