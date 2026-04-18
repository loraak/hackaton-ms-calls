package com.hackaton.ms_calls.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackaton.ms_calls.models.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, UUID> {
    List<Call> findByUserId(UUID userId);

    List<Call> findByClassification(String classification);

    List<Call> findByCreatedAtAfter(LocalDateTime dateTime);
}