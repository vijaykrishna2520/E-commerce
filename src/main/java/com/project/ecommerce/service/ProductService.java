package com.project.ecommerce.service;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Boolean addProduct(ProductEntity productEntity) {
        try {
            ProductEntity productEntitySaved = productRepository.save(productEntity);
            if (productEntitySaved != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getAllProducts() {
        return "All products fetched successfully";
    }

    public String getProductById() {
        return "product with id fetched successfully";
    }

    public String updateProductById() {
        return "product with id updated successfully";
    }

    public String removeProductById() {
        return "product with id removed successfully";
    }


}
