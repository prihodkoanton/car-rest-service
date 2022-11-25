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
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.ModelSpecification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ModelDaoTest {

	@Autowired
	private ModelDao dao;

	@Autowired
	private MakeDao makeDao;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldFindAllByFilter() {
		PageOptions pageOptions = new PageOptions();
		List<Specification<Model>> specifications = Arrays.asList(ModelSpecification.hasMakeId(100L));
		List<Model> expected = Arrays.asList(new Model(100L, "test1", new Make(100L, "Audi")));
		List<Model> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/model_test_data.sql" })
	void shouldSaveAll() {
		Make make1 = new Make("Audi");
		Make make2 = new Make("BMW");
		List<Make> makes = Arrays.asList(make1, make2);
		makeDao.saveAll(makes);

		List<Model> expected = Arrays.asList(new Model("test1", make1), new Model("test2", make2));
		List<Model> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}

}
