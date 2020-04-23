package com.ecommerce.ecommerce.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	public PasswordUtils() {		
	}
	
	/**
	 * Generates a hash using Bcrypt
	 * @param password
	 * @return String
	 */
	public static String generateBCrypt(String password) {
		if (password == null) {
			return null;
		}
		
		log.info("Generating hash using BCrypt");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(password);
	}
}
