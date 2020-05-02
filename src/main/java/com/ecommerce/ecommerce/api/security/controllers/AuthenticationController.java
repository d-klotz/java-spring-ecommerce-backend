package com.ecommerce.ecommerce.api.security.controllers;

import com.ecommerce.ecommerce.api.dto.CustomerDto;
import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.response.Response;
import com.ecommerce.ecommerce.api.security.dto.JwtAuthenticationDto;
import com.ecommerce.ecommerce.api.security.utils.JwtTokenUtil;
import com.ecommerce.ecommerce.api.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Generates and returns a new JWT Token.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<Response<JwtAuthenticationDto>> gerarTokenJwt(
			@Valid @RequestBody JwtAuthenticationDto authenticationDto, BindingResult result)
			throws AuthenticationException {
		Response<JwtAuthenticationDto> response = new Response<>();

		if (result.hasErrors()) {
			log.error("Error found: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		log.info("Generating token for e-mail {}.", authenticationDto.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.getToken(userDetails);
		JwtAuthenticationDto user = new JwtAuthenticationDto();
		user.setEmail(jwtTokenUtil.getUsernameFromToken(token));
		user.setToken(token);

			response.setData(user);
		return ResponseEntity.ok(response);

	}

	/**
	 * Generates a new token with a new expiration time.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Response<JwtAuthenticationDto>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Generating refresh JWT token.");
		Response<JwtAuthenticationDto> response = new Response<>();
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
		JwtAuthenticationDto user = new JwtAuthenticationDto();
		user.setEmail(jwtTokenUtil.getUsernameFromToken(token.get()));
		user.setToken(jwtTokenUtil.refreshToken(token.get()));
		response.setData(user);
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
}
