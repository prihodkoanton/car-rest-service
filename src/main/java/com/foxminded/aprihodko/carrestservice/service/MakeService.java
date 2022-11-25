package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;

public interface MakeService {

	Optional<Make> findByName(String name) throws SQLException;

	Optional<Make> findById(Long id) throws SQLException;

	Make save(Make make);

	List<Make> findAllByFilter(List<Specification<Make>> specifications, PageOptions pageOptions);

	Page<Make> findAllByFilter2(SearchRequest searchRequest);

	void delete(Long id);

	void delete(Make make);
}
