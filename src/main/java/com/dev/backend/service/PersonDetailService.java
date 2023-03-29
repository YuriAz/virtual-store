package com.dev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Person;
import com.dev.backend.repository.PersonRepository;

@Service
public class PersonDetailService implements UserDetailsService {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Person person = personRepository.findByEmail(username);
    if (person == null) {
      throw new UsernameNotFoundException("Person do not found by email");
    }
    return person;
  }
}
