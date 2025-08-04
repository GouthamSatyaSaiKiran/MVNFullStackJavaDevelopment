package com.MVNFullStackProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; 

import com.MVNFullStackProject.service.ReceiptService; 
@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/generate/{bookingId}")
    public ResponseEntity<String> generateReceipt(@PathVariable Long bookingId) {
        return ResponseEntity.ok(receiptService.generate(bookingId));
    }
}

