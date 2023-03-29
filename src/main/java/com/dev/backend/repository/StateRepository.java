package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
