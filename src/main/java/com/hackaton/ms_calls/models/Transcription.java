package com.hackaton.ms_calls.models;

import java.time.Instant;

import lombok.Data;

@Data
public class Transcription {
    private Instant ts;
    private String role;
    private String content;
}