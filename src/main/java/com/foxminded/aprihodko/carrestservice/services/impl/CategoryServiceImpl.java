package com.foxminded.aprihodko.carrestservice.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.repository.CategoryRepository;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> findByName(String name) throws SQLException {
		return categoryRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findCategoryByModels(Long id) throws SQLException {
		return categoryRepository.findCategoryByModels(id);
	}
}
