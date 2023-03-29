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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.model.ProductImage;
import com.dev.backend.service.ProductImageService;

@RestController
@RequestMapping("/api/product-image")
public class ProductImageController {

  @Autowired
  private ProductImageService productImageService;

  @GetMapping
  public List<ProductImage> getImages() {
    return productImageService.listImages();
  }

  @GetMapping("/{id}")
  public List<ProductImage> getById(@PathVariable("id") Long idProduto) {
    return productImageService.getById(idProduto);
  }

  @PostMapping
  public ProductImage createNewImage(@RequestParam("productId") Long productId, @RequestParam MultipartFile file) {
    return productImageService.insert(productId, file);
  }

  @PutMapping
  public ProductImage updateImage(@RequestBody ProductImage image) {
    return productImageService.update(image);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteImage(@PathVariable("id") Long id) {
    productImageService.delete(id);
    return ResponseEntity.ok().build();
  }
}
