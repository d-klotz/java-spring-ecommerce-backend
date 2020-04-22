package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Order;

import java.util.List;

public interface OrderService {

    /**
     * Creates a new order
     * @param order
     * @return created order
     */
    Order createOrder(Order order);

    /**
     * Returns a list of orders by customer id
     * @param id
     * @return list of orders
     */
    List<Order> getOrdersByCustomerId(Long id);

    /**
     * Returns an order by its id
     * @param id
     * @return order
     */
    Order getOrderById(Long id);
}
