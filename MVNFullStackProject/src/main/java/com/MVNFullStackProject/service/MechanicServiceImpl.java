package com.MVNFullStackProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MVNFullStackProject.entity.MechanicRequest;
import com.MVNFullStackProject.repository.MechanicRequestRepository;

@Service
public class MechanicServiceImpl implements MechanicService {

    @Autowired
    private MechanicRequestRepository repo;

    @Override
    public String request(MechanicRequest request) {
        request.setStatus("Pending");
        repo.save(request);
        return "Mechanic request submitted successfully!";
    }

    @Override
    public String getStatus(Long requestId) {
        return repo.findById(requestId)
                   .map(MechanicRequest::getStatus)
                   .orElse("Request not found");
    }
}

