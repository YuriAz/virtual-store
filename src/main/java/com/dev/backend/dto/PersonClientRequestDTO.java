package com.dev.backend.dto;

import org.springframework.beans.BeanUtils;

import com.dev.backend.model.City;
import com.dev.backend.model.Person;

import lombok.Data;

@Data
public class PersonClientRequestDTO {

  private String name;
  private String cpf;
  private String email;
  private String address;
  private String zipCode;
  private City city;

  public Person convertClientIntoPerson(PersonClientRequestDTO personClientRequestDTO) {
    Person person = new Person();
    BeanUtils.copyProperties(personClientRequestDTO, person);
    return person;
  }
}
