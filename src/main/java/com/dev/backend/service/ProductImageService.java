package com.dev.backend.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.model.Product;
import com.dev.backend.model.ProductImage;
import com.dev.backend.repository.ProductImageRepository;
import com.dev.backend.repository.ProductRepository;

@Service
public class ProductImageService {

  @Autowired
  private ProductImageRepository productImageRepository;

  @Autowired
  private ProductRepository productRepository;

  public List<ProductImage> listImages() {
    return productImageRepository.findAll();
  }

  public List<ProductImage> getById(Long idProduto) {
    List<ProductImage> productImages = productImageRepository.findByProductId(idProduto);
    String imagePath = "/home/yuri/Dev/Projects/Java/virtual-store/backend/src/main/resources/static/img/";

    for (ProductImage productImage : productImages) {
      try (InputStream in = new FileInputStream(imagePath + productImage.getName())) {
        productImage.setFile(IOUtils.toByteArray(in));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return productImages;
  }

  public ProductImage insert(Long productId, MultipartFile file) {
    Product product = productRepository.findById(productId).get();
    ProductImage productImage = new ProductImage();
    String imagePath = "/home/yuri/Dev/Projects/Java/virtual-store/backend/src/main/resources/static/img/";

    try {
      if (!file.isEmpty()) {
        byte[] bytes = file.getBytes();
        String imageName = String.valueOf(product.getId()) + file.getOriginalFilename();
        Path path = Paths.get(imagePath + imageName);
        Files.write(path, bytes);
        productImage.setName(imageName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    productImage.setProduct(product);
    productImage.setCreationDate(new Date());
    productImage = productImageRepository.saveAndFlush(productImage);
    return productImage;
  }

  public ProductImage update(ProductImage image) {
    image.setUpdateDate(new Date());
    return productImageRepository.saveAndFlush(image);
  }

  public void delete(Long id) {
    ProductImage image = productImageRepository.findById(id).get();
    productImageRepository.delete(image);
  }
}
