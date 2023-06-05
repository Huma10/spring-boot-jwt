package com.tutorial.mapper;

import com.tutorial.dto.UserDTO;
import com.tutorial.entities.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UserMapper {
	
	// convert  DTO to Model/Entities
	public static User mapToEntities(UserDTO userDTO) {
		if(userDTO == null)
			return new User();
		User user = new User();
		user.setId(userDTO.getId());
		user.setLogin(userDTO.getLogin());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		return user;
	}
	
	// convert User Entites to UserDTO
	
	public static UserDTO mapToDTO(User user) {
		if(user == null)
			return new UserDTO();
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setLogin(user.getLogin());
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	
	
}
