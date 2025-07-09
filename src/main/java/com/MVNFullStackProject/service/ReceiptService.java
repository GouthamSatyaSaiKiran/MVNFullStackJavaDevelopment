package com.MVNFullStackProject.service;

public interface ReceiptService {
    String generate(Long bookingId);
    String getIncomeSummary(); 
}
