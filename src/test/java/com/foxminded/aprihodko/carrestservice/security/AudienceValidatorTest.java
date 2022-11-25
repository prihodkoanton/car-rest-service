package com.foxminded.aprihodko.carrestservice.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

class AudienceValidatorTest {

	@Autowired
	AudienceValidator validator;

	@Test
	void shouldReturnSuccess() {
		String audience = "Test";
		Jwt token = new Jwt(audience, null, null, null, null);
		OAuth2TokenValidatorResult actual = validator.validate(token);
		assertEquals("", actual);
	}

}
