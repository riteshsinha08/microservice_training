package com.example.olxlogin.dto;

import javax.validation.constraints.NotBlank;

public class UserDTO {
	private String id;
	@NotBlank(message="first name can't be null or empty")
	private String firstName;
	@NotBlank(message="last name can't be null or empty")
	private String lastName;
	@NotBlank(message="user name can't be null or empty")
	private String userName;
	@NotBlank(message="password can't be null or empty")
	private String password;
	@NotBlank(message="email can't be null or empty")
	private String email;
	private int phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}
