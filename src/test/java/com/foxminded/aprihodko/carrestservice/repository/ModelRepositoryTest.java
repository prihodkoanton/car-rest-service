package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ModelRepositoryTest {

	@Autowired
	ModelRepository modelRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindById() {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		Model expected = new Model(100L, 2020, make);
		Model actual = modelRepository.findById(100L).orElse(null);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindByMakeId() throws SQLException {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		List<Model> expected = Arrays.asList(new Model(100L, 2020, make));
		List<Model> actual = modelRepository.findByMakeId(100L);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindByYear() throws SQLException {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		List<Model> expected = Arrays.asList(new Model(100L, 2020, make));
		List<Model> actual = modelRepository.findByYear(2020);
		assertEquals(expected, actual);
	}
}
