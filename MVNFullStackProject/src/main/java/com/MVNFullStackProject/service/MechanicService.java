package com.MVNFullStackProject.service;

import com.MVNFullStackProject.entity.MechanicRequest;

public interface MechanicService {
    String request(MechanicRequest request);
    String getStatus(Long requestId);
}
