package com.ecommerce.ecommerce.api.services;

import com.ecommerce.ecommerce.api.entities.Customer;

import java.util.Optional;

public interface CustomerService {

    /**
     * Creates a new customer
     * @param customer
     * @return new customer data
     */
 Customer createCustomer(Customer customer);

    /**
     * Returns a customer by his id
     * @param id
     * @return customer
     */
 Customer getCustomerById(Long id);

    /**
     * Returns a customer by his email
     * @param email
     * @return customer
     */
 Optional<Customer> getCustomerByEmail(String email);
}
