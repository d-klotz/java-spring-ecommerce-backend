package com.ecommerce.ecommerce.api.services.impl;

import com.ecommerce.ecommerce.api.entities.Product;
import com.ecommerce.ecommerce.api.repository.ProductRepository;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        log.info("Getting product by id {}", id);
        return this.productRepository.getOne(id);
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
}
