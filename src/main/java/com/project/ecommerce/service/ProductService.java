package com.project.ecommerce.service;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.exception.InternalServerErrorException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Boolean addProduct(ProductEntity productEntity) {
        try {
            ProductEntity productEntitySaved = productRepository.save(productEntity);
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<ProductEntity> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public ProductEntity getProductById(Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (!productEntity.isPresent()) {
            throw new ResourceNotFoundException("Resource with " + productId + " Not Found");
        }
        return productEntity.get();
    }

    public Boolean updateProductById(Long productId, ProductEntity productEntityNew) {
        ProductEntity productEntityOld = getProductById(productId);
        productEntityNew.setProductId(productEntityOld.getProductId());
        try {
            productRepository.save(productEntityNew);
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server error");
        }
    }

    public Boolean removeProductById(Long productId) {
        ProductEntity productEntity = getProductById(productId);
        try {
            productRepository.delete(productEntity);
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

}
