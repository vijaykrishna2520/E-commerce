package com.project.ecommerce.service;

import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.exception.InternalServerErrorException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public CategoryEntity getCategoryByCategoryId(Long categoryId) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        return categoryEntity.orElse(null);
    }


    public Long addCategory(CategoryEntity categoryEntity) {
        try {
            CategoryEntity categoryEntitySaved = categoryRepository.save(categoryEntity);
            return categoryEntitySaved.getCategoryId();
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public List<CategoryEntity> getAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

    public CategoryEntity getCategoryById(Long categoryId) {
        Optional<CategoryEntity> CategoryEntity = categoryRepository.findById(categoryId);
        if (!CategoryEntity.isPresent()) {
            throw new ResourceNotFoundException("category with " + categoryId + " Not Found");
        }
        return CategoryEntity.get();
    }

    public Boolean updateCategoryById(Long categoryId, CategoryEntity categoryEntityNew) {
        CategoryEntity categoryEntityOld = getCategoryById(categoryId);
        categoryEntityNew.setCategoryId(categoryEntityOld.getCategoryId());
        try {
            categoryRepository.save(categoryEntityNew);
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server error");
        }
    }

    public Boolean removeCategoryById(Long categoryId) {
        CategoryEntity categoryEntity = getCategoryById(categoryId);
        try {
            categoryRepository.delete(categoryEntity);
            return true;
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error");
        }
    }

}
