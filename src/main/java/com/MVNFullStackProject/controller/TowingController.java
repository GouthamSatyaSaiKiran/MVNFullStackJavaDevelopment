package com.MVNFullStackProject.controller;

import com.MVNFullStackProject.entity.TowingRequest;
import com.MVNFullStackProject.service.TowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/towing")
@CrossOrigin(origins = "*")
public class TowingController {

    @Autowired
    private TowingService service;

    @PostMapping("/request")
    public ResponseEntity<TowingRequest> createRequest(@RequestBody TowingRequest request) {
        return ResponseEntity.ok(service.createRequest(request));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<TowingRequest>> getAllRequests() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<TowingRequest> updateStatus(@PathVariable Long id, @PathVariable String status) {
        TowingRequest updated = service.updateStatus(id, status);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
