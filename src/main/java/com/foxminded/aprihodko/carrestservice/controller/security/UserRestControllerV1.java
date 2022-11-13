package com.foxminded.aprihodko.carrestservice.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.security.dto.UserDto;
import com.foxminded.aprihodko.carrestservice.service.security.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/users/")
@RequiredArgsConstructor
public class UserRestControllerV1 {

	private final UserService userService;

	@GetMapping(value = "{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
		User user = userService.findById(id).orElseGet(null);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		UserDto result = UserDto.fromUser(user);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
