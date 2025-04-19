package com.example.repository;

import com.example.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    Optional<Queue> findByName(String name);
}