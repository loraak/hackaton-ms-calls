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

import com.hackaton.ms_calls.DTO.EmergencyCallRequest;
import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.services.CallService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/calls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CallController {

    private final CallService callService;

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
}