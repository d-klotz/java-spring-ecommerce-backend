package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.entities.Product;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        log.info("Getting product {}", id);
        Product product = this.productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value="/")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("Getting all products");
        List<Product> products = this.productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
