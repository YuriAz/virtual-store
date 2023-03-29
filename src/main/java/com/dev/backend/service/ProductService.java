package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Product;
import com.dev.backend.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> listProducts() {
    return productRepository.findAll();
  }

  public Product getById(Long id) {
    Product product = productRepository.findById(id).get();
    return product;
  }

  public Product insert(Product product) {
    product.setCreationDate(new Date());
    Product newProduct = productRepository.saveAndFlush(product);
    return newProduct;
  }

  public Product update(Product product) {
    product.setUpdateDate(new Date());
    return productRepository.saveAndFlush(product);
  }

  public void delete(Long id) {
    Product product = productRepository.findById(id).get();
    productRepository.delete(product);
  }
}
