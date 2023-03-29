package com.dev.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dto.PersonClientRequestDTO;
import com.dev.backend.model.Person;
import com.dev.backend.repository.PersonClientRepository;

@Service
public class PersonClientService {

  @Autowired
  private PersonClientRepository personClientRepository;

  @Autowired
  private PersonPermissionService permissionService;

  @Autowired
  private EmailService emailService;

  public Person insert(PersonClientRequestDTO personClientRequestDTO) {
    Person person = new PersonClientRequestDTO().convertClientIntoPerson(personClientRequestDTO);
    person.setCreationDate(new Date());
    Person newPerson = personClientRepository.saveAndFlush(person);
    permissionService.boundPersonPermissionToClient(newPerson);
    // emailService.sendTextEmail(newPerson.getEmail(), "Virtual Store register",
    // "Your new account was successfully created.");
    Map<String, Object> propertiesMap = new HashMap<>();
    propertiesMap.put("name", newPerson.getName());
    propertiesMap.put("message", "Register successfull");
    emailService.sendTemplateEmail(newPerson.getEmail(), "Register successfull", propertiesMap);
    return newPerson;
  }
}
