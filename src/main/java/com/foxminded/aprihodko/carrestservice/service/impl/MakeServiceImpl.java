package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.MakeDao;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

	private final MakeRepository makeRepository;
	private final MakeDao dao;

	@Override
	@Transactional(readOnly = true)
	public List<Make> findByName(String name) throws SQLException {
		List<Make> makes = makeRepository.findByName(name);
//				orElseThrow(
//				() -> new UsernameNotFoundException("IN findByName - make with name ='" + name + "' does not found"));
		log.info("IN findByName - make: {} successfully found", makes);
		return makes;
	}

	@Override
	public Optional<Make> findById(Long id) throws SQLException {
		Make make = makeRepository.findById(id).get();
//				orElseThrow(
//				() -> new UsernameNotFoundException("IN findById - make with id ='" + id + "' does not found"));
		log.info("IN findById - make: {} successfully found", make);
		return Optional.of(make);
	}

	@Override
	public Make save(Make make) {
		Make savedMake = makeRepository.save(make);
		log.info("IN save - make: {} successfully saved", savedMake);
		return savedMake;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Make> findAllByFilter(List<Specification<Make>> specifications, PageOptions pageOptions) {
		List<Make> makes = dao.findAllByFilter(specifications, pageOptions);
		log.info("IN findAllFiltered - : {} makes found", makes);
		return makes;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Make> findAllByFilter2(SearchRequest searchRequest) {
		SearchSpecification<Make> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		Page<Make> page = makeRepository.findAll(specification, pageable);
		log.info("IN findAllFiltered - : {} page found", page);
		return page;
	}

	@Override
	public void delete(Long id) {
		makeRepository.deleteById(id);
		log.info("IN delete (by id) - make with id: {} successfully deleted", id);
	}

	@Override
	public void delete(Make make) {
		makeRepository.delete(make);
		log.info("IN delete (by object) - make: {} successfully deleted", make);
	}

}
