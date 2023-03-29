package com.dev.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Person;
import com.dev.backend.repository.PersonRepository;

@Service
public class PersonManagmentService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private EmailService emailService;

  @Autowired
  PasswordEncoder passwordEncoder;

  public String requestRecoveryCode(String email) {
    Person person = personRepository.findByEmail(email);
    person.setPasswordRecoveryCode(getPasswordRecoveryCode(person.getId()));
    person.setPasswordRecoveryExpirationDate(new Date());
    personRepository.saveAndFlush(person);
    emailService.sendTextEmail(person.getEmail(), "Password Recovery", person.getPasswordRecoveryCode());
    return "Message sent!";
  }

  public String changePassword(Person person) {
    Person newPerson = personRepository.findByEmailAndPasswordRecoveryCode(person.getEmail(),
        person.getPasswordRecoveryCode());
    if (newPerson != null) {
      Date dateDif = new Date(new Date().getTime() - newPerson.getPasswordRecoveryExpirationDate().getTime());
      if (dateDif.getTime() / 1000 < 900) {
        newPerson.setPassword(passwordEncoder.encode(person.getPassword()));
        newPerson.setPasswordRecoveryCode(null);
        personRepository.saveAndFlush(newPerson);
        return "Password successfully changed!";
      } else {
        return "Time expired! Request a new code";
      }
    } else {
      return "Email or password recovery code do not found!";
    }
  }

  private String getPasswordRecoveryCode(Long id) {
    DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
    return format.format(new Date()) + id;
  }
}
