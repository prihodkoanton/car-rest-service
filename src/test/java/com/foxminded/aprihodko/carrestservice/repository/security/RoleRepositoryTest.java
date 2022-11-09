package com.foxminded.aprihodko.carrestservice.repository.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.security.Role;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class RoleRepositoryTest {

	@Autowired
	RoleRepository roleRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/role_test_data.sql" })
	void shouldFindByName() {
		Role expected = new Role();
		expected.setId(100L);
		expected.setName("ADMIN");
		Role actual = roleRepository.findByName(expected.getName()).orElseGet(null);
		assertEquals(expected, actual);
	}
}
