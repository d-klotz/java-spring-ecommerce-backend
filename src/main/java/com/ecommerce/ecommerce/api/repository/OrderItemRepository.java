package com.ecommerce.ecommerce.api.repository;

import com.ecommerce.ecommerce.api.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
