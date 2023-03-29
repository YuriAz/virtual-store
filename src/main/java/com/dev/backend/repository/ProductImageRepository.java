package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

  List<ProductImage> findByProductId(Long id);
}
