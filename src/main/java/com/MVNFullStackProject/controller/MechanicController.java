package com.MVNFullStackProject.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.MVNFullStackProject.service.MechanicService;
import com.MVNFullStackProject.entity.MechanicRequest;

@RestController
@RequestMapping("/mechanic")
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/services")
    public List<String> getServices() {
        return Arrays.asList("Flat Tyre", "Engine Breakdown", "Battery Jumpstart");
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestService(@RequestBody MechanicRequest request) {
        return ResponseEntity.ok(mechanicService.request(request));
    }

    @GetMapping("/status/{requestId}")
    public ResponseEntity<String> getStatus(@PathVariable Long requestId) {
        return ResponseEntity.ok(mechanicService.getStatus(requestId));
    }
}
