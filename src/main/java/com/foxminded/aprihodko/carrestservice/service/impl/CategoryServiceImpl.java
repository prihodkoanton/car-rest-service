package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.CategoryRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.CategoryDao;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryDao dao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> findById(Long id) throws SQLException {
		Category category = categoryRepository.findById(id).get();
//				orElseThrow(() -> new UsernameNotFoundException("IN findById - category with id ='" + id + "' does not found"));
		log.info("IN findById - category: {} successfully found", category);
		return Optional.of(category);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> findByUsername(String username) throws SQLException {
		Category category = categoryRepository.findByName(username).get();
//				.orElseThrow(() -> new UsernameNotFoundException(				"IN findByUsername - category with username = '" + username + "' does not found{}"));
		log.info("IN findByUsername - category: {} successfully found", category);
		return Optional.of(category);
	}

	@Override
	public Category save(Category category) {
		Category saved = categoryRepository.save(category);
		log.info("IN save - category: {} successfully saved", saved);
		return saved;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllByFilter(List<Specification<Category>> specifications, PageOptions pageOptions) {
		List<Category> categories = dao.findAllByFilter(specifications, pageOptions);
		log.info("IN findAllFiltered - : {} categories found", categories);
		return categories;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Category> findAllByFilter2(SearchRequest searchRequest) {
		SearchSpecification<Category> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		Page<Category> pageCategory = categoryRepository.findAll(specification, pageable);
		log.info("IN findAllFiltered2 - : {} pageCategory found", pageCategory);
		return pageCategory;
	}

	@Override
	public void delete(Long id) {
		categoryRepository.deleteById(id);
		log.info("IN delete (by id) - category with id: {} successfully deleted", id);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
		log.info("IN delete (by object) - category: {} successfully deleted", category);
	}
}
