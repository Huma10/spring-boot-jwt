package com.tutorial.services;

import org.springframework.stereotype.Service;

import com.tutorial.dto.ProfileDTO;
import com.tutorial.dto.UserDTO;
import com.tutorial.entities.User;
import com.tutorial.mapper.UserMapper;
import com.tutorial.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public UserDTO getProfileData(String login) {
		User user = userRepository.findByLogin(login);
		UserDTO userDTO = UserMapper.mapToDTO(user);
		return userDTO;
	}
	
}
