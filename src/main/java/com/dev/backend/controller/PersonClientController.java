package com.dev.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.dto.PersonClientRequestDTO;
import com.dev.backend.model.Person;
import com.dev.backend.service.PersonClientService;

@RestController
@RequestMapping("/api/client")
public class PersonClientController {

  @Autowired
  private PersonClientService personClientService;

  @PostMapping
  public Person createNewPerson(@RequestBody PersonClientRequestDTO personClientRequestDTO) {
    return personClientService.insert(personClientRequestDTO);
  }
}
