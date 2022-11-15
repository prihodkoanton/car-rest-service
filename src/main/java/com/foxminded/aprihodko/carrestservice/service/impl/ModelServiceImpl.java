package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.ModelDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;
	private final ModelDao dao;

	@Override
	public List<Model> findByYear(int year) throws SQLException {
		return modelRepository.findByYear(year);
	}

	@Override
	public List<Model> findByMakeId(Long id) throws SQLException {
		return modelRepository.findByMakeId(id);
	}

	@Override
	public List<Model> findAllFiltered(List<Specification<Model>> specifications, PageOptions pageOptions) {
		return dao.findAllByFilter(specifications, pageOptions);
	}

	@Override
	public Page<Model> findAllFiltered2(SearchRequest searchRequest) {
		SearchSpecification<Model> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		return modelRepository.findAll(specification, pageable);
	}
}
