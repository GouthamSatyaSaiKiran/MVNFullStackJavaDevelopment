package com.MVNFullStackProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MVNFullStackProject.entity.Receipt;
import com.MVNFullStackProject.repository.ReceiptRepository;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public String generate(Long bookingId) {
        if (receiptRepository.findByBookingId(bookingId).isPresent()) {
            return "Receipt already generated for booking ID: " + bookingId;
        }

        Receipt receipt = new Receipt();
        receipt.setBookingId(bookingId);
        receipt.setGeneratedAt(LocalDateTime.now());
        receipt.setAmount(200.0); 
        receipt.setPaymentMode("Online");
        receipt.setStatus("Paid");

        receiptRepository.save(receipt);
        return "Receipt generated for booking ID: " + bookingId;
    }

    @Override
    public String getIncomeSummary() {
        List<Receipt> receipts = receiptRepository.findAll();
        double total = receipts.stream().mapToDouble(Receipt::getAmount).sum();
        return "Total Income: ₹" + String.format("%.2f", total);
    }
}


