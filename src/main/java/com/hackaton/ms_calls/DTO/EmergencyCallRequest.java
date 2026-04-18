package com.hackaton.ms_calls.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class EmergencyCallRequest {
    private UUID user_id;
    private String latitud;
    private String longitud;
}