package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Brand;
import com.dev.backend.repository.BrandRepository;

@Service
public class BrandService {

  @Autowired
  private BrandRepository brandRepository;

  public List<Brand> listBrands() {
    return brandRepository.findAll();
  }

  public Brand insert(Brand brand) {
    brand.setCreationDate(new Date());
    Brand newBrand = brandRepository.saveAndFlush(brand);
    return newBrand;
  }

  public Brand update(Brand brand) {
    brand.setUpdateDate(new Date());
    return brandRepository.saveAndFlush(brand);
  }

  public void delete(Long id) {
    Brand brand = brandRepository.findById(id).get();
    brandRepository.delete(brand);
  }
}
