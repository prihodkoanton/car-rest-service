package com.foxminded.aprihodko.carrestservice.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.security.Status;
import com.foxminded.aprihodko.carrestservice.model.security.User;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

	private Long id;
	private String name;
	private String status;

	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(name);
		user.setStatus(Status.valueOf(status));
		return user;
	}

	public static AdminUserDto fromUser(User user) {
		AdminUserDto adminUserDto = new AdminUserDto();
		adminUserDto.setId(user.getId());
		adminUserDto.setName(user.getUsername());
		adminUserDto.setStatus(user.getStatus().name());
		return adminUserDto;
	}
}
