package com.ecommerce.ecommerce.api.services.impl;

import com.ecommerce.ecommerce.api.entities.Order;
import com.ecommerce.ecommerce.api.repository.OrderRepository;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long id) {
        return this.orderRepository.findByCustomerId(id);
    }

    @Override
    public Order getOrderById(Long id) {
        return this.orderRepository.getOne(id);
    }
}
