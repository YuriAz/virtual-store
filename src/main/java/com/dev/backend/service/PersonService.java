package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Person;
import com.dev.backend.repository.PersonRepository;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public List<Person> listPeople() {
    return personRepository.findAll();
  }

  public Person insert(Person person) {
    person.setCreationDate(new Date());
    Person newPerson = personRepository.saveAndFlush(person);
    return newPerson;
  }

  public Person update(Person person) {
    person.setUpdateDate(new Date());
    return personRepository.saveAndFlush(person);
  }

  public void delete(Long id) {
    Person person = personRepository.findById(id).get();
    personRepository.delete(person);
  }
}
