package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Person findByEmail(String email);

  Person findByEmailAndPasswordRecoveryCode(String email, String passwordRecoveryCode);
}
