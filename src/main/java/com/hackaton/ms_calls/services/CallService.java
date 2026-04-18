package com.hackaton.ms_calls.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.repositories.CallRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CallService {

    private final CallRepository callRepository;

    public List<Call> getAll() {
        return callRepository.findAll();
    }

    public List<Call> getByUserId(UUID userId) {
        return callRepository.findByUserId(userId);
    }
}