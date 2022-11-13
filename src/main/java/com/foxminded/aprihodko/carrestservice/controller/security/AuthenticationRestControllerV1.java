package com.foxminded.aprihodko.carrestservice.controller.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.security.dto.AuthenticationRequestDto;
import com.foxminded.aprihodko.carrestservice.security.jwt.JwtTokenProvider;
import com.foxminded.aprihodko.carrestservice.service.security.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("login")
	public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
		try {
			String username = requestDto.getUsername();
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
			User user = userService.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

			String token = jwtTokenProvider.createToken(username, user.getRoles());

			Map<Object, Object> response = new HashMap<>();
			response.put("username", username);
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username or password");
		}
	}
}
