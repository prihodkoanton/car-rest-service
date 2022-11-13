package com.foxminded.aprihodko.carrestservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.security.jwt.JwtUser;
import com.foxminded.aprihodko.carrestservice.security.jwt.JwtUserFactory;
import com.foxminded.aprihodko.carrestservice.service.security.UserService;

import lombok.extern.slf4j.Slf4j;

@Service("jwtUserDetailsService")
@Slf4j
public abstract class JwtUserDetailsService implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public JwtUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username).orElseGet(null);
		if (user == null) {
			throw new UsernameNotFoundException("User with username: " + username + " not found");
		}
		JwtUser jwtUser = JwtUserFactory.create(user);
		log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
		return jwtUser;
	}
}
