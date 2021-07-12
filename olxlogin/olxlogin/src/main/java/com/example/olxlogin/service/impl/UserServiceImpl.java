package com.example.olxlogin.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.olxlogin.dto.UserDTO;
import com.example.olxlogin.model.UserEntity;
import com.example.olxlogin.repository.UserRepository;
import com.example.olxlogin.service.UserService;
import com.example.olxlogin.utils.JwtUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public Boolean userLogout(String token) {
		return true;
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		List<UserEntity> users = userRepository.findByUserName(userDTO.getUserName());
		if (!users.isEmpty()) {
			// throw new UsernameAlreadyExistsException(userDTO.getUserName());
		}
		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		userEntity.setRoles("ROLE_USER");
		List<UserEntity> listofUser = userRepository.findAll();
		int id = listofUser.get(listofUser.size() - 1).getId() + 1;
		userEntity.setId(id);
		return mapper.map(userRepository.save(userEntity), UserDTO.class);
	}

	@Override
	public UserDTO getUser(String token) {
		String username = jwtUtils.extractUsername(token);
		return mapper.map(userRepository.findByUserName(username).get(0), UserDTO.class);
	}

}
