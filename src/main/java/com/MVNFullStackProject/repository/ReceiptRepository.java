package com.MVNFullStackProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.MVNFullStackProject.entity.Receipt;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findByBookingId(Long bookingId);
}
