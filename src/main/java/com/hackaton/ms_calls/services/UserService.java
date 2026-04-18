package com.hackaton.ms_calls.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.hackaton.ms_calls.DTO.UserWithLastCallDTO;
import com.hackaton.ms_calls.models.Call;
import com.hackaton.ms_calls.models.User;
import com.hackaton.ms_calls.repositories.CallRepository;
import com.hackaton.ms_calls.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final CallRepository callRepository;

    public Optional<UserWithLastCallDTO> getById(UUID id) {
        log.info("Obteniendo usuario con id: {}", id);
        return userRepository.findById(id).map(user -> {
            Call lastCall = callRepository
                    .findTopByUserIdOrderByCreatedAtDesc(id)
                    .orElse(null);
            return UserWithLastCallDTO.builder()
                    .user(user)
                    .lastCall(lastCall)
                    .build();
        });
    }
}