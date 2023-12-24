package com.project.ecommerce.controller;

import com.project.ecommerce.entity.ProductEntity;
import com.project.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "400", description = "Bad request please check the request fields are correct", content = @io.swagger.v3.oas.annotations.media.Content(
//                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    example = "Bad Request for the Api"
                            )
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal server error, Something went wrong", content = @io.swagger.v3.oas.annotations.media.Content(
//                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    example = "Internal server error"
                            )
                    )),
                    @ApiResponse(responseCode = "403", description = "Resource Forbidden", content = @io.swagger.v3.oas.annotations.media.Content(
//                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    example = "You are not authorized to access this Api"
                            )
                    )),
                    @ApiResponse(responseCode = "404", description = "Resource not found", content = @io.swagger.v3.oas.annotations.media.Content(
//                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    example = "Products not found"
                            )
                    ))
            }
    )
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ProductEntity getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProductById(@PathVariable("productId") Long productId, @RequestBody ProductEntity productEntity) {
        productService.updateProductById(productId, productEntity);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeProductById(@PathVariable("productId") Long productId) {
        productService.removeProductById(productId);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

}
