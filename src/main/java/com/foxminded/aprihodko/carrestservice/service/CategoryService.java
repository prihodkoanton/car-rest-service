package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;

public interface CategoryService {

	Optional<Category> findById(Long id) throws SQLException;

	List<Category> findByUsername(String username) throws SQLException;

	Category save(Category category);

	List<Category> findAllByFilter(List<Specification<Category>> specifications, PageOptions pageOptions);

	Page<Category> findAllByFilter2(SearchRequest searchRequest);

	void delete(Long id);

	void delete(Category category);
}
