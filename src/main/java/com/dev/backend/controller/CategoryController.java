package com.dev.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.model.Category;
import com.dev.backend.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<Category> getCategories() {
    return categoryService.listCategories();
  }

  @PostMapping
  public Category createNewCategory(@RequestBody Category category) {
    return categoryService.insert(category);
  }

  @PutMapping
  public Category updateCategory(@RequestBody Category category) {
    return categoryService.update(category);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
    categoryService.delete(id);
    return ResponseEntity.ok().build();
  }
}
