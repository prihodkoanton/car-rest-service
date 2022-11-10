package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.MakeDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

	private final MakeRepository makeRepository;
	private final MakeDao dao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Make> findByName(String name) throws SQLException {
		return makeRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Make> findAllFiltered(List<Specification<Make>> specifications, PageOptions pageOptions) {
		return dao.findAllByFilter(specifications, pageOptions);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Make> findAllFiltered2(SearchRequest searchRequest) {
		SearchSpecification<Make> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		return makeRepository.findAll(specification, pageable);
	}
}
