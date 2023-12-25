package com.project.ecommerce.service;

import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.exception.InternalServerErrorException;
import com.project.ecommerce.repository.CategoryRepository;
import com.project.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void moveProductIntoCategory(Long productId, Long categoryId) {
        ProductEntity productEntity = productService.getProductById(productId);
        CategoryEntity categoryEntity = categoryService.getCategoryById(categoryId);
        try {
            productEntity.getCategoryEntities().add(categoryEntity);
            productRepository.save(productEntity);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    @Transactional
    public void moveProductsIntoCategory(Long categoryId, List<Long> productIds) {
        try {
            CategoryEntity categoryEntity = categoryService.getCategoryByCategoryId(categoryId);
            productIds.forEach(productId -> {
                ProductEntity productEntity = productService.getProductByProductId(productId);
                if (productEntity != null && categoryEntity != null) {
                    categoryEntity.getProductEntities().add(productEntity);
                    LOGGER.info("Product " + productId + " moved to Category " + categoryId + " successfully");
                } else {
                    LOGGER.error("Product " + productId + " not found and moving failed");
                }
            });
            categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    //spring batch is needed for this functionality
    @Transactional
    public void addNewProductsAndMoveToCategory(Long categoryId, List<ProductEntity> products) {
        try {
            List<ProductEntity> productsSaved = productRepository.saveAll(products);
            LOGGER.info("Products saved to database successfully");
            CategoryEntity categoryEntity = categoryService.getCategoryByCategoryId(categoryId);
            if(categoryEntity!=null){
                boolean isProductsMovingToCategorySucceed = categoryEntity.getProductEntities().addAll(productsSaved);
                if(!isProductsMovingToCategorySucceed){
                    LOGGER.error("Products not added to the Category {} something went wrong please check ",categoryId);
                    throw new InternalServerErrorException("Internal server error Products not added to Category "+categoryId);
                }
                LOGGER.info("Products successfully added to the Category "+categoryId);
            }

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }
}
