package com.foxminded.aprihodko.carrestservice.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.MakeSpecification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MakeDaoTest {

	@Autowired
	private MakeDao dao;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
	void shouldFindAllByFilter() {
		PageOptions pageOptions = new PageOptions();
		List<Specification<Make>> specifications = Arrays.asList(MakeSpecification.hasName("Audi"));
		List<Make> expected = Arrays.asList(new Make(100L, "Audi"), new Make(103L, "Audi"));
		List<Make> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
	void shouldSaveAll() {
		List<Make> expected = Arrays.asList(new Make("Test1"), new Make("Test2"));
		List<Make> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}
}
