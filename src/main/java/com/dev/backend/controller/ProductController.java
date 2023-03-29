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

import com.dev.backend.model.Product;
import com.dev.backend.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getProducts() {
    return productService.listProducts();
  }

  @GetMapping("/{id}")
  public Product getById(@PathVariable("id") Long id) {
    return productService.getById(id);
  }

  @PostMapping
  public Product createNewProduct(@RequestBody Product product) {
    return productService.insert(product);
  }

  @PutMapping
  public Product updateProduct(@RequestBody Product product) {
    return productService.update(product);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
  }
}
