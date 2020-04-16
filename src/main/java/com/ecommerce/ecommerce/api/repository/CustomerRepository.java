package com.ecommerce.ecommerce.api.repository;

import com.ecommerce.ecommerce.api.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
