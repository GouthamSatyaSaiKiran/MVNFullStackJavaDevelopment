package com.MVNFullStackProject.service;

import com.MVNFullStackProject.entity.TowingRequest;
import java.util.List;

public interface TowingService {
    TowingRequest createRequest(TowingRequest request);
    List<TowingRequest> getAllRequests();
    TowingRequest updateStatus(Long id, String newStatus);
}