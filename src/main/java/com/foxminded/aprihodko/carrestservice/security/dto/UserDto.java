package com.foxminded.aprihodko.carrestservice.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.security.User;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

	private Long id;
	private String username;

	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		return user;
	}

	public static UserDto fromUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		return userDto;
	}
}
