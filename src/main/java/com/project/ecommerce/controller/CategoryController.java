package com.project.ecommerce.controller;

import com.project.ecommerce.entity.CategoryEntity;
import com.project.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/alive", method = RequestMethod.GET)
    public String isAlive() {
        return "Categorys service is live!";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addCategory(@RequestBody CategoryEntity CategoryEntity) {
        Long categoryIdSaved = categoryService.addCategory(CategoryEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{productId}")
                .buildAndExpand(categoryIdSaved)
                .toUri();
        httpHeaders.setLocation(location);
        return new ResponseEntity<String>("Category added successfully", httpHeaders, HttpStatus.CREATED);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public CategoryEntity getCategoryById(@PathVariable("categoryId") Long CategoryId) {
        return categoryService.getCategoryById(CategoryId);
    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategoryById(@PathVariable("categoryId") Long CategoryId, @RequestBody CategoryEntity CategoryEntity) {
        categoryService.updateCategoryById(CategoryId, CategoryEntity);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCategoryById(@PathVariable("categoryId") Long CategoryId) {
        categoryService.removeCategoryById(CategoryId);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

}
