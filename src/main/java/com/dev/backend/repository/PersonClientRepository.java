package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Person;

public interface PersonClientRepository extends JpaRepository<Person, Long> {
}
