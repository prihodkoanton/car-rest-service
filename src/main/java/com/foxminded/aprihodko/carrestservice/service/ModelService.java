package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;

public interface ModelService {

	Optional<Model> findById(Long id) throws SQLException;

	List<Model> findByName(String name) throws SQLException;

	Model save(Model model);

	List<Model> findByMakeId(Long id) throws SQLException;

	List<Model> findAllByFilter(List<Specification<Model>> specifications, PageOptions pageOptions);

	Page<Model> findAllByFilter2(SearchRequest searchRequest);

	void delete(Long id);

	void delete(Model model);
}
