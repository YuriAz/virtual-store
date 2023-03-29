package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
