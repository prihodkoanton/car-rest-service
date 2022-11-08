package com.foxminded.aprihodko.carrestservice.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {

//	private final AuthenticationManager authenticationManager;
//	private final JwtTokenProvider jwtTokenProvider;
//	private final UserService userService;

//	@PostMapping("login")
//	public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
//		try {
//			String username = requestDto.getUsername();
//			authenticationManager
//					.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
//			User user = userService.findByUsername(username).orElse(null);
//			if (user == null) {
//				throw new UsernameNotFoundException("User with username: " + username + " not found");
//			}
//
//			String token = jwtTokenProvider.createToken(username, user.getRoles());
//
//			Map<Object, Object> response = new HashMap<>();
//			response.put("username", username);
//			response.put("token", token);
//			return ResponseEntity.ok(response);
//		} catch (AuthenticationException e) {
//			throw new BadCredentialsException("Invalid username or password");
//		}
//	}
}
