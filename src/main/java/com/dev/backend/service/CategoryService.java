package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Category;
import com.dev.backend.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> listCategories() {
    return categoryRepository.findAll();
  }

  public Category insert(Category category) {
    category.setCreationDate(new Date());
    Category newCategory = categoryRepository.saveAndFlush(category);
    return newCategory;
  }

  public Category update(Category category) {
    category.setUpdateDate(new Date());
    return categoryRepository.saveAndFlush(category);
  }

  public void delete(Long id) {
    Category category = categoryRepository.findById(id).get();
    categoryRepository.delete(category);
  }
}
