package com.MVNFullStackProject.service;

import java.util.List;
import com.MVNFullStackProject.entity.Branch;

public interface BranchService {
    List<Branch> getAll();
    String getContact(Long branchId);
    Branch save(Branch branch);

}

