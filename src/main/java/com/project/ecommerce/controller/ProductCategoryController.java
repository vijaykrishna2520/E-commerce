package com.project.ecommerce.controller;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/{categoryId}/productsIds", method = RequestMethod.POST)
    public ResponseEntity<String> sample(@PathVariable(value = "categoryId", required = true) Long categoryId,
                                         @RequestBody List<Long> productsIds) {
        productCategoryService.moveProductsIntoCategory(categoryId, productsIds);
        return new ResponseEntity<>("successful", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{categoryId}", method = RequestMethod.POST)
    public void addNewProductsAndMoveToCategory(@PathVariable("categoryId") Long categoryId,
                                               @RequestBody List<ProductEntity> products) {
        productCategoryService.addNewProductsAndMoveToCategory(categoryId,products);
    }
}
