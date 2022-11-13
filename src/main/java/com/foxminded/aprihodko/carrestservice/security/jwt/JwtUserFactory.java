package com.foxminded.aprihodko.carrestservice.security.jwt;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.foxminded.aprihodko.carrestservice.model.security.Role;
import com.foxminded.aprihodko.carrestservice.model.security.Status;
import com.foxminded.aprihodko.carrestservice.model.security.User;

public final class JwtUserFactory {

	public JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getUsername(), user.getPassword(), user.getStatus().equals(Status.ACTIVE),
				user.getUpdted(), mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
		return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(toList());
	}
}
