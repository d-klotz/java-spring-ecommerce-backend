package com.ecommerce.ecommerce.api.security.services;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.security.JwtUserFactory;
import com.ecommerce.ecommerce.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> customer = customerService.getCustomerByEmail(username);

		if (customer.isPresent()) {
			return JwtUserFactory.create(customer.get());
		}

		throw new UsernameNotFoundException("No customer found with this email");
	}

}
