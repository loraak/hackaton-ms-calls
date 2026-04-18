package com.hackaton.ms_calls.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.http.MediaType;
import com.hackaton.ms_calls.DTO.EmergencyCallRequest;
import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.services.CallNotificationService;
import com.hackaton.ms_calls.services.CallService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/calls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallController {

    private final CallService callService;
    private final CallNotificationService callNotificationService;

    @GetMapping
    public ResponseEntity<List<Call>> getAll() {
        return ResponseEntity.ok(callService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Call>> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(callService.getByUserId(userId));
    }

    @GetMapping("/classification/{classification}")
    public ResponseEntity<List<Call>> getByClassification(@PathVariable String classification) {
        return ResponseEntity.ok(callService.getByClassification(classification));
    }

    @PostMapping("/emergency")
    public ResponseEntity<Call> createEmergencyCall(@RequestBody EmergencyCallRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(callService.createEmergencyCall(request));
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        callNotificationService.addEmitter(emitter);
        return emitter;
    }
}