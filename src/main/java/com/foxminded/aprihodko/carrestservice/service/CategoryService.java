package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.carrestservice.model.Category;

public interface CategoryService {

	Optional<Category> findByName(String name) throws SQLException;

	List<Category> findCategoryByModels(Long id) throws SQLException;
}
