package com.example.olxlogin.dto;

import javax.validation.constraints.NotBlank;

public class AuthenticationRequestDTO {

	@NotBlank(message="User Name can't be empty or null")
	private String username;
	@NotBlank(message="Password can't be empty or null")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
