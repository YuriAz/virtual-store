package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Person;
import com.dev.backend.service.PersonService;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {

  @Autowired
  private PersonService personService;

  @GetMapping
  public List<Person> getCities() {
    return personService.listPeople();
  }

  @PostMapping
  public Person createNewPerson(@RequestBody Person person) {
    return personService.insert(person);
  }

  @PutMapping
  public Person updatePerson(@RequestBody Person person) {
    return personService.update(person);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
    personService.delete(id);
    return ResponseEntity.ok().build();
  }
}
