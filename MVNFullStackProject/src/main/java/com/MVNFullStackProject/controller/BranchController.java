package com.MVNFullStackProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MVNFullStackProject.entity.Branch;
import com.MVNFullStackProject.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;
    
    @PostMapping("/add")
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) {
        Branch saved = branchService.save(branch);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/list")
    public List<Branch> getAllBranches() {
        return branchService.getAll();
    }

    @GetMapping("/contact/{branchId}")
    public ResponseEntity<String> contactBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(branchService.getContact(branchId));
    }
}
