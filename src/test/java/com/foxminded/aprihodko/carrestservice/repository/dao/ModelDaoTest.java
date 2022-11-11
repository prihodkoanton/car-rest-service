package com.foxminded.aprihodko.carrestservice.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ModelDaoTest {

	@Autowired
	private ModelDao dao;

	@Test
	void shouldFindAllByFilter() {
		PageOptions pageOptions = new PageOptions();
		List<Specification<Model>> specifications = new ArrayList<>();
		List<Model> expected = Arrays.asList(new Model(100L, 2020, new Make(100L, "Audi")),
				new Model(101L, 2022, new Make(101L, "BMW")));
		List<Model> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	void shouldSaveAll() {
		List<Model> expected = Arrays.asList(new Model(2020, new Make(102L, "Audi")),
				new Model(2022, new Make(103L, "BMW")));
		List<Model> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}

}
