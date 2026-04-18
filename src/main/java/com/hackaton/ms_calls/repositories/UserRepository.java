package com.hackaton.ms_calls.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackaton.ms_calls.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}