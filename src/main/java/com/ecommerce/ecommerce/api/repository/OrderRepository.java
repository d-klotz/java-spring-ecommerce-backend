package com.ecommerce.ecommerce.api.repository;

import com.ecommerce.ecommerce.api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long id);
}
