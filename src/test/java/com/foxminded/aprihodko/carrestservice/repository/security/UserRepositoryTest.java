package com.foxminded.aprihodko.carrestservice.repository.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.security.User;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindByUsername() {
		User expected = new User();
		expected.setId(100L);
		expected.setUsername("Anton");
		expected.setPassword("12345678");
		User actual = userRepository.findByUsername("Anton").orElseGet(null);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/user_test_data.sql" })
	void shouldFindById() {
		User expected = new User();
		expected.setId(100L);
		expected.setUsername("Anton");
		expected.setPassword("12345678");
		User actual = userRepository.findById(100L).orElseGet(null);
		assertEquals(expected, actual);
	}

}
