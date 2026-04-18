package com.hackaton.ms_calls.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hackaton.ms_calls.models.Cita;
import com.hackaton.ms_calls.repositories.CitaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CitaService {

    private final CitaRepository citaRepository;

    public List<Cita> getAll() {
        log.info("Obteniendo todas las citas");
        return citaRepository.findAll();
    }

    public Optional<Cita> getById(Long id) {
        log.info("Obteniendo cita con id: {}", id);
        return citaRepository.findById(id);
    }
}