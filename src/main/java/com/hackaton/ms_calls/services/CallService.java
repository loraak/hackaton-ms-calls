package com.hackaton.ms_calls.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.hackaton.ms_calls.DTO.EmergencyCallRequest;
import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.repositories.CallRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public Call createEmergencyCall(EmergencyCallRequest request) {
        log.info("Creando llamada de emergencia para userId: {}", request.getUser_id());
        log.info("Ubicación: lat={}, lng={}", request.getLatitud(), request.getLongitud());

        Call call = Call.builder()
                .userId(request.getUser_id())
                .callType("boton")
                .status("en_curso")
                .classification("sin_clasificar")
                .latitud(request.getLatitud())
                .longitud(request.getLongitud())
                .createdAt(LocalDateTime.now())
                .transcriptions(new ArrayList<>())
                .build();

        Call saved = callRepository.save(call);
        log.info("Llamada creada con id: {}", saved.getId());
        return saved;
    }

}