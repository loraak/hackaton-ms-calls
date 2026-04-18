package com.hackaton.ms_calls.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.hackaton.ms_calls.services.ObjectListConverter;

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
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "family_group_id")
    private UUID familyGroupId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Convert(converter = ObjectListConverter.class)
    @Column(name = "chronic_conditions", columnDefinition = "text")
    private List<Object> chronicConditions;

    @Convert(converter = ObjectListConverter.class)
    @Column(name = "medications", columnDefinition = "text")
    private List<Object> medications;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;

    @Column(name = "main_summary", columnDefinition = "text")
    private String mainSummary;

    @Column(name = "is_onboarded")
    private Boolean isOnboarded;

    @Column(name = "call_count")
    private Integer callCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}