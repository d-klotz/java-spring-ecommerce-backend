package com.ecommerce.ecommerce.api.repository;

import com.ecommerce.ecommerce.api.entities.Product;
import com.ecommerce.ecommerce.api.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
