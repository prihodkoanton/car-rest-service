package com.foxminded.aprihodko.carrestservice.security.jwt;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtUser implements UserDetails {

	private final Long id;
	private final String name;
	private final String password;
	private final boolean enabled;
	private final Date lastPasswordResetDate;
	private final Collection<? extends GrantedAuthority> authorities;

	@Override
	public String getUsername() {
		return name;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
