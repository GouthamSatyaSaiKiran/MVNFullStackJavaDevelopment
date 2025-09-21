package com.MVNFullStackProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MVNFullStackProject.entity.Branch;
import com.MVNFullStackProject.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;
    
    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }


    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    public String getContact(Long branchId) {
        return branchRepository.findById(branchId)
            .map(Branch::getContactNumber)
            .orElse("Branch not found");
    }
}