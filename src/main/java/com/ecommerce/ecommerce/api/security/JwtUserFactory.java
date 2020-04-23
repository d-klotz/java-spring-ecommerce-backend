package com.ecommerce.ecommerce.api.security;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converts and generates a JwtUser based on a customer
	 * 
	 * @param customer
	 * @return JwtUser
	 */
	public static JwtUser create(Customer customer) {
		return new JwtUser(customer.getId(), customer.getEmail(), customer.getPassword(),
				mapToGrantedAuthorities(customer.getProfile()));
	}

	/**
	 * Converts the user profile to the format used by Spring Security
	 * 
	 * @param profile
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Profile profile) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profile.toString()));
		return authorities;
	}

}
