package com.hackaton.ms_calls.models;

import java.security.Timestamp;
import java.util.List;
import java.util.UUID;
import com.hackaton.ms_calls.services.TranscriptionListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "calls")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "call_type")
    private String callType;

    @Column(name = "status")
    private String status;

    @Column(name = "classification")
    private String classification;

    @Convert(converter = TranscriptionListConverter.class)
    @Column(name = "transcriptions", columnDefinition = "jsonb")
    private List<Transcription> transcriptions;

    @Column(name = "summary_markdown")
    private String summaryMarkdown;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "created_at")
    private Timestamp createdAt; 
}
