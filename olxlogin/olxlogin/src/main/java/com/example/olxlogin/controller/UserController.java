package com.example.olxlogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.olxlogin.dto.AuthenticationRequestDTO;
import com.example.olxlogin.dto.UserDTO;
import com.example.olxlogin.service.UserService;
import com.example.olxlogin.utils.JwtUtils;

@CrossOrigin
@RestController
@RefreshScope
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<String> getAuthToken(@Valid @RequestBody AuthenticationRequestDTO authRequestDTO) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
					authRequestDTO.getPassword()));

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(authRequestDTO.getUsername());
		}
		return new ResponseEntity<>(jwtUtils.generateToken(authRequestDTO.getUsername()), HttpStatus.OK);
	}

	@GetMapping("/token/validate")
	public ResponseEntity<Boolean> isTokenValid(@RequestHeader("Authorization") String token) {
		String jwtToken = token.substring(7);
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.extractUsername(jwtToken));
		boolean isValidToken = jwtUtils.validateToken(jwtToken, userDetails);
		if (isValidToken)
			return new ResponseEntity<>(true, HttpStatus.OK);
		else
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/user")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.registerUser(userDTO), HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<UserDTO> getUser(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(userService.getUser(token.substring(7)), HttpStatus.OK);
	}

	@DeleteMapping("/logout")
	public ResponseEntity<Boolean> userLogout(@RequestHeader("Authorization") String token) {
		return new ResponseEntity<>(userService.userLogout(token), HttpStatus.OK);
	}
}
