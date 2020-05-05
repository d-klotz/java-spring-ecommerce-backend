package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.dto.CustomerDto;
import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.response.Response;
import com.ecommerce.ecommerce.api.security.utils.JwtTokenUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);
    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    CustomerService customerService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<Response<CustomerDto>> createCustomer(@Valid @RequestBody CustomerDto customerDto) {

        log.info("Creating customer {}", customerDto);
        Customer customer = this.convertCustomerDtoToCustomer(customerDto);
        Customer newCustomer = this.customerService.createCustomer(customer);
        CustomerDto newCustomerDto = convertCustomerToCustomerDto(newCustomer);
        Response<CustomerDto> response = new Response<>();
        response.setData(newCustomerDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        log.info("Getting customer {}", id);
        Customer customer = this.customerService.getCustomerById(id);
        CustomerDto customerDto = convertCustomerToCustomerDto(customer);
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping()
    public ResponseEntity<Response<CustomerDto>> getCustomerByToken(HttpServletRequest request) {
        log.info("Getting user by Token");
        Response<CustomerDto> response = new Response<>();
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
            token = Optional.of(token.get().substring(7));
        }

        if (!token.isPresent()) {
            response.getErrors().add("Token not found.");
        } else if (!jwtTokenUtil.isTokenValid(token.get())) {
            response.getErrors().add("Token expired or invalid.");
        }

        if (!response.getErrors().isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Customer> customer = this.customerService
                .getCustomerByEmail(jwtTokenUtil.getUsernameFromToken(token.get()));
        CustomerDto customerDto = convertCustomerToCustomerDto(customer.get());
        response.setData(customerDto);
        return ResponseEntity.ok(response);
    }

    private CustomerDto convertCustomerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(Optional.of(customer.getId()));
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setProfile(customer.getProfile());
        customerDto.setMainPaymentMethod(customer.getMainPaymentMethod());
        customerDto.setAddress(customer.getAddress());
        customerDto.setNumber(customer.getNumber());
        customerDto.setComplement(customer.getComplement());
        return customerDto;
    }

    private Customer convertCustomerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setProfile(customerDto.getProfile());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(PasswordUtils.generateBCrypt(customerDto.getPassword()));
        customer.setMainPaymentMethod(customerDto.getMainPaymentMethod());
        customer.setAddress(customerDto.getAddress());
        customer.setNumber(customerDto.getNumber());
        customer.setComplement(customerDto.getComplement());
        return customer;
    }

}
