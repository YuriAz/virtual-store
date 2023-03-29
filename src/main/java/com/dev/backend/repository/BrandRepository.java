package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
