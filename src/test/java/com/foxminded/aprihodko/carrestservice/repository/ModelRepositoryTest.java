package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	MakeRepository makeRepository;

	@Autowired
	ModelRepository modelRepository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindById() {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		Model expected = new Model(100L, "test1", make);
		Model actual = modelRepository.findById(100L).orElse(null);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindByMakeId() throws SQLException {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		List<Model> expected = Arrays.asList(new Model(100L, "test1", make));
		List<Model> actual = modelRepository.findByMakeId(100L);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindByYear() throws SQLException {
		Make make = new Make();
		make.setId(100L);
		make.setName("Audi");
		List<Model> expected = Arrays.asList(new Model(100L, "test1", make));
		List<Model> actual = modelRepository.findByName("test1");
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldDelete() throws SQLException {
		modelRepository.deleteById(100L);
		Optional<Model> actual = modelRepository.findById(100L);
		assertTrue(actual.isEmpty());
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldCreateNew() throws SQLException {
		Make makeToSave = new Make("created");
		Make make = makeRepository.save(makeToSave);
		Model modelToSave = new Model("test1", make);
		Model expected = modelRepository.save(modelToSave);
		assertNotNull(expected.getId());
		Model actual = modelRepository.findById(expected.getId()).get();
		assertEquals(expected, actual);
	}
}
