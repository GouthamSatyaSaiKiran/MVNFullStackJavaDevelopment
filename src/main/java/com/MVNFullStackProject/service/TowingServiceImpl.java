package com.MVNFullStackProject.service;

import com.MVNFullStackProject.entity.TowingRequest;
import com.MVNFullStackProject.repository.TowingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TowingServiceImpl implements TowingService {

    @Autowired
    private TowingRequestRepository repository;

    @Override
    public TowingRequest createRequest(TowingRequest request) {
        return repository.save(request);
    }

    @Override
    public List<TowingRequest> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public TowingRequest updateStatus(Long id, String newStatus) {
        Optional<TowingRequest> optional = repository.findById(id);
        if (optional.isPresent()) {
            TowingRequest request = optional.get();
            request.setStatus(newStatus);
            return repository.save(request);
        }
        return null;
    }
}

