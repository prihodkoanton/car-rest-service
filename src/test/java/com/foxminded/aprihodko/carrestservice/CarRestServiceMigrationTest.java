package com.foxminded.aprihodko.carrestservice;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class CarRestServiceMigrationTest extends BaseDaoTest {

	public static final String QUERY_FOR_COURSES = "select column_name from information_schema.columns where table_name ='categories'";

	@Autowired
	JdbcTemplate template;

	@Test
	void shouldCreateTables() {

		List<String> strings = template.queryForList(QUERY_FOR_COURSES, String.class);

		List<String> expected = Arrays.asList("id", "category_name");

		assertTrue(strings.containsAll(expected));
	}
}
