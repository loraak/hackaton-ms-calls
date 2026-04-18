package com.hackaton.ms_calls.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Call> getByClassification(String classification) {
        return callRepository.findByClassification(classification);
    }

    public Call createEmergencyCall() {
        UUID defaultUserId = UUID.fromString("1d38411b-3f6b-4706-91f8-23d8b399afb5");
        
        Call call = Call.builder()
                .userId(defaultUserId)
                .callType("boton")
                .status("en_curso")
                .classification("roja")
                .createdAt(LocalDateTime.now())
                .transcriptions(new ArrayList<>())
                .build();
        return callRepository.save(call);
    }
}