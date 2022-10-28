package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Make;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MakeRepositoryTest {

	@Autowired
	MakeRepository makeRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
	void shouldFindById() {
		Make expected = new Make(100L, "Audi");
		Make actual = makeRepository.findById(100L).orElse(null);
		assertEquals(expected, actual);
	}
}
