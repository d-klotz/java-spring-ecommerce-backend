package com.ecommerce.ecommerce.api.security.dto;

public class JwtAuthenticationDto {
	
	private String email;
	private String password;

	public JwtAuthenticationDto() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String senha) {
		this.password = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDto [email=" + email + ", password=" + password + "]";
	}

}
