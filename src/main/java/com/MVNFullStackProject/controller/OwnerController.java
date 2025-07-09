package com.MVNFullStackProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MVNFullStackProject.service.ReceiptService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/income-summary")
    public ResponseEntity<String> getIncomeSummary() {
        return ResponseEntity.ok(receiptService.getIncomeSummary());
    }
}
