package com.ecommerce.ecommerce.api.repository;

import com.ecommerce.ecommerce.api.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
