package com.example.olxlogin.service;

import com.example.olxlogin.dto.AuthenticationRequestDTO;
import com.example.olxlogin.dto.UserDTO;

public interface UserService {

	public Boolean userLogout(String token);
	public UserDTO registerUser(UserDTO userDTO);
	public UserDTO getUser(String token);
}
