package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.repository.CategoryRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.CategoryDao;
import com.foxminded.aprihodko.carrestservice.service.impl.CategoryServiceImpl;

@SpringBootTest(classes = CategoryServiceImpl.class)
class CategoryServiceTest {

	@MockBean
	CategoryRepository categoryRepository;

	@MockBean
	CategoryDao categoryDao;

	@Autowired
	CategoryService categoryService;

	@Test
	void shoudlFindByName() throws SQLException {
		Category category = new Category(100L, "test");
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
		Optional<Category> expected = categoryRepository.findByName(category.getName());
		Optional<Category> actual = categoryService.findByName(category.getName());
		assertEquals(expected, actual);
	}

//	@Test
//	void shouldFindCategoryByModels() throws SQLException {
//
//	}
}
