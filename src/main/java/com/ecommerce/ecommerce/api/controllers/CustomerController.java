package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {

        log.info("Creating customer {}", customer);
        this.customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

}
