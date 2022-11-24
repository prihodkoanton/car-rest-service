package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.CarRepostiry;
import com.foxminded.aprihodko.carrestservice.repository.dao.CarDao;
import com.foxminded.aprihodko.carrestservice.service.CarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

	private final CarRepostiry repostiry;
	private final CarDao dao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findById(Long id) {
		Car car = repostiry.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("IN findById - car with id ='" + id + "' does not found"));
		log.info("IN findById - car: {} successfully found", car);
		return Optional.of(car);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByYear(int year) throws SQLException {
		List<Car> cars = repostiry.findByYear(year);
		log.info("IN findByYear - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findByName(String name) throws SQLException {
		Car car = repostiry.findByName(name).orElseThrow(
				() -> new UsernameNotFoundException("IN findById - car with id ='" + name + "' does not found"));
		log.info("IN findById - car: {} successfully found", car);
		return Optional.of(car);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByMakeId(Long id) throws SQLException {
		List<Car> cars = repostiry.findByMakeId(id);
		log.info("IN findByMakeId - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByModelId(Long id) throws SQLException {
		List<Car> cars = repostiry.findByModelId(id);
		log.info("IN findByMakeId - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findAllByFilter(List<Specification<Car>> specifications, PageOptions pageOptions)
			throws SQLException {
		List<Car> cars = dao.findAllByFilter(specifications, pageOptions);
		log.info("IN findAllFiltered - : {} cars found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Car> findAllByFilter2(SearchRequest searchRequest) throws SQLException {
		SearchSpecification<Car> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		Page<Car> pageCar = repostiry.findAll(specification, pageable);
		log.info("IN findAllFiltered2 - : {} pageCar found", pageCar);
		return pageCar;
	}

	@Override
	@Transactional
	public Car save(Car car) throws SQLException {
		Car saved = repostiry.save(car);
		log.info("IN save - car: {} successfully saved", saved);
		return car;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findCarsByCategory(Long id) throws SQLException {
		List<Car> cars = repostiry.findCarsByCategory(id);
		log.info("IN findCarsByCategory - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	public void delete(Long id) throws SQLException {
		repostiry.deleteById(id);
		log.info("IN delete (by id) - car with id: {} successfully deleted", id);
	}

	@Override
	public void delete(Car car) throws SQLException {
		repostiry.delete(car);
		log.info("IN delete (by object) - car: {} successfully deleted", car);
	}

	@Override
	public List<Car> findAllFilteredAsListDTO(SearchRequest searchRequest) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
