package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.dto.CustomerDto;
import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {

        log.info("Creating customer {}", customerDto);
        Customer customer = this.convertCustomerDtoToCustomer(customerDto);
        Customer newCustomer = this.customerService.createCustomer(customer);
        CustomerDto newCustomerDto = convertCustomerToCustomerDto(newCustomer);
        return ResponseEntity.ok(newCustomerDto);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        log.info("Getting customer {}", id);
        Customer customer = this.customerService.getCustomerById(id);
        CustomerDto customerDto = convertCustomerToCustomerDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    private CustomerDto convertCustomerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(Optional.of(customer.getId()));
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setProfile(customer.getProfile());
        customerDto.setMainPaymentMethod(customer.getMainPaymentMethod());
        return customerDto;
    }

    private Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setProfile(customerDto.getProfile());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(PasswordUtils.generateBCrypt(customerDto.getPassword()));
        customer.setMainPaymentMethod(customerDto.getMainPaymentMethod());
        return customer;
    }

}
