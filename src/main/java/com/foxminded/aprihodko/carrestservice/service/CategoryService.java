package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

public interface CategoryService {

	Optional<Category> findById(Long id) throws SQLException;

	Optional<Category> findByUsername(String username) throws SQLException;

	Category save(Category category);

	List<Category> findCategoryByModels(Long id) throws SQLException;

	List<Category> findAllFiltered(List<Specification<Category>> specifications, PageOptions pageOptions);

	Page<Category> findAllFiltered2(SearchRequest searchRequest);

	void delete(Long id);

	void delete(Category category);
}
