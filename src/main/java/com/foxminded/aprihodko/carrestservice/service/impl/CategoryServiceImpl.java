package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.CategoryRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.CategoryDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryDao dao;

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

	@Override
	public List<Category> findAllFiltered(List<Specification<Category>> specifications, PageOptions pageOptions) {
		return dao.findAllByFilter(specifications, pageOptions);
	}

	@Override
	public Page<Category> findAllFiltered2(SearchRequest searchRequest) {
		SearchSpecification<Category> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		return categoryRepository.findAll(specification, pageable);
	}
}
