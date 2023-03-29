package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.City;
import com.dev.backend.repository.CityRepository;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<City> listCities() {
    return cityRepository.findAll();
  }

  public City insert(City city) {
    city.setCreationDate(new Date());
    City newCity = cityRepository.saveAndFlush(city);
    return newCity;
  }

  public City update(City city) {
    city.setUpdateDate(new Date());
    return cityRepository.saveAndFlush(city);
  }

  public void delete(Long id) {
    City city = cityRepository.findById(id).get();
    cityRepository.delete(city);
  }
}
