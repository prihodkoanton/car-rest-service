package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

public interface ModelService {

	List<Model> findByYear(int year) throws SQLException;

	List<Model> findByMakeId(Long id) throws SQLException;

	List<Model> findAllFiltered(List<Specification<Model>> specifications, PageOptions pageOptions);

	Page<Model> findAllFiltered2(SearchRequest searchRequest);
}
