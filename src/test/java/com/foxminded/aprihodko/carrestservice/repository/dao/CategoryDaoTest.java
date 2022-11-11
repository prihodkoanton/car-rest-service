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

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryDaoTest {

	@Autowired
	private CategoryDao dao;

	@Test
	void shouldFindAllByFilter() {
		PageOptions pageOptions = new PageOptions();
		List<Specification<Category>> specifications = new ArrayList<>();
//		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//		CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
//		Root<Category> root = query.from(Category.class);
//
//		specifications.stream().map(it -> it.toPredicate(root, query, criteriaBuilder)).collect(toList())
//				.toArray(new Predicate[0]);
		List<Category> expected = Arrays.asList(new Category(100L, "Sedan"), new Category(101L, "Suv"),
				new Category(102L, "Pickup"));
		List<Category> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	void shouldSaveAll() {
		List<Category> expected = Arrays.asList(new Category("Test1"), new Category("Test2"));
		List<Category> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}
}
