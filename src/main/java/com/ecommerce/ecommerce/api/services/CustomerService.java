package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Customer;

public interface CustomerService {

    /**
     * Creates a new customer
     * @param customer
     * @return new customer data
     */
 Customer createCustomer(Customer customer);

 Customer getCustomerById(Long id);
}
