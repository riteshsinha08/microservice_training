package com.example.olxlogin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.olxlogin.model.UserEntity;
import com.example.olxlogin.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userList = userRepository.findByUserName(username);
		if (userList == null || userList.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity user = userList.get(0);
		String[] roles = user.getRoles().split(",");
		List<GrantedAuthority> authorities = Arrays.stream(roles).map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
		return new User(username, user.getPassword(), authorities);
	}

}
