package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    Product createProduct(Product product);

    List<Product> getAllProducts();
}
