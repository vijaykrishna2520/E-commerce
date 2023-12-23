package com.project.ecommerce.controller;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/alive", method = RequestMethod.GET)
    public String isAlive() {
        return "products service is live!";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addProduct(@RequestBody ProductEntity productEntity) {
        productService.addProduct(productEntity);
        return new ResponseEntity<String>("Product added successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ProductEntity getProductById(@PathVariable("productId") Long id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProductById(@PathVariable("productId") Long id, @RequestBody ProductEntity productEntity) {
        productService.updateProductById(id, productEntity);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeProductById(@PathVariable("productId") Long id) {
        productService.removeProductById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

}
