package com.ecommerce.ecommerce.api.services.impl;

import com.ecommerce.ecommerce.api.entities.OrderItem;
import com.ecommerce.ecommerce.api.repository.OrderItemRepository;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
