package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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
	void shoudlFindByUsername() throws SQLException {
		Category category = new Category(100L, "test");
		when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));
		Optional<Category> expected = categoryRepository.findByName(category.getName());
		Optional<Category> actual = categoryService.findByUsername(category.getName());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindById() throws SQLException {
		Category category = new Category(100L, "test");
		when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
		Optional<Category> expected = categoryRepository.findById(category.getId());
		Optional<Category> actual = categoryService.findById(category.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdate() {
		Category category = new Category(100L, "test");
		when(categoryRepository.save(category)).thenReturn(category);
		Category expected = categoryRepository.save(category);
		Category actual = categoryService.save(category);
		assertEquals(expected, actual);
	}

	@Test
	void shouldCreateNew() {
		Category category = new Category("test");
		when(categoryRepository.save(category)).thenReturn(new Category(100L, category.getName()));
		Category expected = categoryRepository.save(category);
		Category actual = categoryService.save(category);
		assertNotNull(actual.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeleteById() {
		categoryService.delete(100L);
		verify(categoryRepository).deleteById(100L);
	}

	@Test
	void shouldDeleteByObject() throws SQLException {
		Category category = new Category(100L, "test");
		when(categoryRepository.save(category)).thenReturn(category);
		Category categoryToDeleted = categoryRepository.save(category);
		categoryService.delete(categoryToDeleted);
		Optional<Category> shouldBeNull = categoryRepository.findById(categoryToDeleted.getId());
		assertTrue(shouldBeNull.isEmpty());
	}
}
