package com.project.ecommerce.controller;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        if (productService.addProduct(productEntity)) {
            return new ResponseEntity<String>("Product added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Product adding failed please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public String getProductById() {
        return productService.getProductById();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public String updateProductById() {
        return productService.updateProductById();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public String removeProductById() {
        return productService.removeProductById();
    }

}
