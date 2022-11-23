package com.foxminded.aprihodko.carrestservice.service.impl;

import static java.util.stream.Collectors.toList;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.dto.CarList;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.CarRepostiry;
import com.foxminded.aprihodko.carrestservice.repository.dao.CarDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
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
	public Optional<CarDTO> findById(Long id) {
		Car car = repostiry.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("IN findById - car with id ='" + id + "' does not found"));
		log.info("IN findById - car: {} successfully found", car);
		return Optional.of(CarDTO.fromCar(car));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDTO> findByYear(int year) throws SQLException {
		List<Car> cars = repostiry.findByYear(year);
		log.info("IN findByYear - : {} cars successfully found", cars);
		return CarList.fromCarDTO(cars);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CarDTO> findByName(String name) throws SQLException {
		Car car = repostiry.findByName(name).orElseThrow(
				() -> new UsernameNotFoundException("IN findById - car with id ='" + name + "' does not found"));
		log.info("IN findById - car: {} successfully found", car);
		return Optional.of(CarDTO.fromCar(car));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDTO> findByMakeId(Long id) throws SQLException {
		List<Car> cars = repostiry.findByMakeId(id);
		log.info("IN findByMakeId - : {} cars successfully found", cars);
		return CarList.fromCarDTO(cars);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDTO> findByModelId(Long id) throws SQLException {
		List<Car> cars = repostiry.findByModelId(id);
		log.info("IN findByMakeId - : {} cars successfully found", cars);
		return CarList.fromCarDTO(cars);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDTO> findAllByFilter(List<Specification<Car>> specifications, PageOptions pageOptions)
			throws SQLException {
		List<CarDTO> cars = dao.findAllByFilter(specifications, pageOptions).stream().map(CarDTO::fromCar)
				.collect(toList());
		log.info("IN findAllFiltered - : {} cars found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CarDTO> findAllByFilter2(SearchRequest searchRequest) throws SQLException {
		SearchSpecification<Car> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = searchRequest.asPageble();
		Page<CarDTO> pageCar = repostiry.findAll(specification, pageable).map(CarDTO::fromCar);
		log.info("IN findAllFiltered2 - : {} pageCar found", pageCar);
		return pageCar;
	}

	@Override
	public List<CarDTO> findAllFilteredAsListDTO(SearchRequest searchRequest) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public CarDTO save(Car car) throws SQLException {
		Car saved = repostiry.save(car);
		log.info("IN save - car: {} successfully saved", saved);
		return CarDTO.fromCar(car);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDTO> findCarsByCategory(Long id) throws SQLException {
		List<Car> cars = repostiry.findCarsByCategory(id);
		log.info("IN findCarsByCategory - : {} cars successfully found", cars);
		return CarList.fromCarDTO(cars);
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

}
