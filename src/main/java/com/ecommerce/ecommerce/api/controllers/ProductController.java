package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.entities.Product;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        log.info("Creating product {}", product);
        this.productService.createProduct(product);


        return ResponseEntity.ok(product);
    }
}
