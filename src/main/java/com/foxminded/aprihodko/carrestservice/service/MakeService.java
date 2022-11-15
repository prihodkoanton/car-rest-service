package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

public interface MakeService {

	Optional<Make> findByName(String name) throws SQLException;

	List<Make> findAllFiltered(List<Specification<Make>> specifications, PageOptions pageOptions);

	Page<Make> findAllFiltered2(SearchRequest searchRequest);
}
