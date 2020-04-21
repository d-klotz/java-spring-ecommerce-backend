package com.ecommerce.ecommerce.api.services.impl;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.repository.CustomerRepository;
import com.ecommerce.ecommerce.api.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Saving customer {}", customer);
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        log.info("Getting customer by id {}", id);
        return this.customerRepository.getOne(id);
    }
}
