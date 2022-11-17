package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.ModelDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;
	private final ModelDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Model> findByYear(int year) throws SQLException {
		List<Model> models = modelRepository.findByYear(year);
		log.info("IN findByYear - {} models found", models.size());
		return models;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Model> findById(Long id) throws SQLException {
		Model model = modelRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("IN findById - model with id ='" + id + "' does not found"));
		log.info("IN findById - make: {} successfully found", model);
		return Optional.of(model);
	}

	@Override
	@Transactional
	public Model save(Model model) {
		Model savedModel = modelRepository.save(model);
		log.info("IN save - model: {} successfully saved", savedModel);
		return savedModel;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Model> findByMakeId(Long id) throws SQLException {
		return modelRepository.findByMakeId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Model> findAllFiltered(List<Specification<Model>> specifications, PageOptions pageOptions) {
		return dao.findAllByFilter(specifications, pageOptions);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Model> findAllFiltered2(SearchRequest searchRequest) {
		SearchSpecification<Model> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		return modelRepository.findAll(specification, pageable);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		modelRepository.deleteById(id);
		log.info("IN delete (by id) - model with id: {} successfully deleted", id);
	}

	@Override
	@Transactional
	public void delete(Model model) {
		modelRepository.delete(model);
		log.info("IN delete (by object) - model: {} successfully deleted", model);
	}
}
