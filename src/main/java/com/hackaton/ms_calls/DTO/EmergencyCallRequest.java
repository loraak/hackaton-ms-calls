package com.hackaton.ms_calls.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class EmergencyCallRequest {
    private UUID userId;
    private String latitud;
    private String longitud;
}