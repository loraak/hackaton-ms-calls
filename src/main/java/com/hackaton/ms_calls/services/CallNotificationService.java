package com.hackaton.ms_calls.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.repositories.CallRepository;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CallNotificationService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private final CallRepository callRepository;
    private LocalDateTime lastCheck = LocalDateTime.now();

    @Scheduled(fixedDelay = 5000)
    public void checkNewCalls() {
        List<Call> newCalls = callRepository.findByCreatedAtAfter(lastCheck);
        lastCheck = LocalDateTime.now();
        
        if (!newCalls.isEmpty()) {
            log.info("Nuevas llamadas detectadas: {}", newCalls.size());
            newCalls.forEach(call -> broadcast(call.getId().toString()));
        }
    }

    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    private void broadcast(String data) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(data));
            } catch (Exception e) {
                emitters.remove(emitter);
            }
        });
    }
}