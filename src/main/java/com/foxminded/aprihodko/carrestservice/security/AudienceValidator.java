package com.foxminded.aprihodko.carrestservice.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

	private final String audience;

	@Override
	public OAuth2TokenValidatorResult validate(Jwt token) {
		OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);
		if (token.getAudience().contains(audience)) {
			return OAuth2TokenValidatorResult.success();
		}
		return OAuth2TokenValidatorResult.failure(error);
	}
}
