package com.foxminded.aprihodko.carrestservice.controller.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.security.dto.AdminUserDto;
import com.foxminded.aprihodko.carrestservice.service.security.UserService;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

	private final UserService userService;

	public AdminRestControllerV1(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "user/{id}")
	public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
		try {
			User user = userService.findById(id).orElseThrow();

			if (user == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			AdminUserDto result = AdminUserDto.fromUser(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
