package com.hackaton.ms_calls.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackaton.ms_calls.models.Transcription;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class TranscriptionListConverter implements AttributeConverter<List<Transcription>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Transcription> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

    @Override
    public List<Transcription> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, 
                objectMapper.getTypeFactory().constructCollectionType(List.class, Transcription.class));
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to list", e);
        }
    }
}