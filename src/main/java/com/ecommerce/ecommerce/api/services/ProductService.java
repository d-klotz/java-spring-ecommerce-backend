package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Product;

public interface ProductService {

    Product getProductById(Long id);

    Product createProduct(Product product);
}
