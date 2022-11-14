package com.foxminded.aprihodko.carrestservice.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JwtUserDetailsServiceTest {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Test
	void shouldLoadUserByUsername() {
		UserDetails actual = jwtUserDetailsService.loadUserByUsername("Anton");
		assertEquals("", actual);
	}
}
