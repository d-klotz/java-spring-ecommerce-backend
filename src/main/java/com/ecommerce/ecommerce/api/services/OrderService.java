package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Order;

public interface OrderService {

    /**
     * Creates a new order
     * @param order
     * @return created order
     */
    Order createOrder(Order order);
}
