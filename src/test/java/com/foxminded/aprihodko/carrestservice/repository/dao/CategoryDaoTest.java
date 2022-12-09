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

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CategorySpecification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryDaoTest extends BaseDaoTest {

	@Autowired
	private CategoryDao dao;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/category_test_data.sql" })
	void shouldFindAllByFilter() {
		PageOptions pageOptions = new PageOptions();
		List<Specification<Category>> specifications = Arrays.asList(CategorySpecification.hasName("Sedan"));
		List<Category> expected = Arrays.asList(new Category(100L, "Sedan"));
		List<Category> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/category_test_data.sql" })
	void shouldSaveAll() {
		List<Category> expected = Arrays.asList(new Category("Test1"), new Category("Test2"));
		List<Category> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}
}
