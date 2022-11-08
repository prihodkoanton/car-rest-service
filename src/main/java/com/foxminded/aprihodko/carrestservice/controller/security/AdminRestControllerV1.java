package com.foxminded.aprihodko.carrestservice.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/admin/")
@RequiredArgsConstructor
public class AdminRestControllerV1 {

//	private final UserService userService;

//	@GetMapping(value = "user/{ud}")
//	public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
//		try {
//			User user = userService.findById(id).orElseThrow();
//
//			if (user == null) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			AdminUserDto result = AdminUserDto.fromUser(user);
//			return new ResponseEntity<>(result, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//	}
}
