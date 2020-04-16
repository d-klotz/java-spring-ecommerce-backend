package com.ecommerce.ecommerce.api.services.impl;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer createCustomer(Customer customer) {
        System.out.println(customer.getEmail());
        return null;
    }
}
