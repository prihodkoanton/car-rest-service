package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchSpecification;
import com.foxminded.aprihodko.carrestservice.repository.CarRepostiry;
import com.foxminded.aprihodko.carrestservice.repository.CategoryRepository;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.CarDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.CategoryDao;
import com.foxminded.aprihodko.carrestservice.service.CarService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

	private final CarRepostiry carRepostiry;
	private final ModelRepository modelRepository;
	private final MakeRepository makeRepository;
	private final CategoryRepository categoryRepository;
	private final CategoryDao categoryDao;
	private final CarDao dao;

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findById(Long id) {
		Car car = carRepostiry.findById(id)
				.orElseThrow(() -> new IllegalAccessError("IN findById - car with id ='" + id + "' does not found"));
		log.info("IN findById - car: {} successfully found", car);
		return Optional.of(car);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByYear(int year) throws SQLException {
		List<Car> cars = carRepostiry.findByYear(year);
		log.info("IN findByYear - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByYearGreaterThanEqual(int year) throws SQLException {
		List<Car> cars = carRepostiry.findByYearGreaterThanEqual(year);
		log.info("IN findByYearGreaterThanEqual - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findByYearLessThanEqual(int year) throws SQLException {
		List<Car> cars = carRepostiry.findByYearLessThanEqual(year);
		log.info("IN findByYearLessThanEqual - : {} cars successfully found", cars);
		return cars;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findByMakeId(Long id) throws SQLException {
		Optional<Car> car = carRepostiry.findByMakeId(id);
		log.info("IN findByMakeId - : {} cars successfully found", car);
		return car;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findByModelId(Long id) throws SQLException {
		Optional<Car> car = carRepostiry.findByModelId(id);
		log.info("IN findByMakeId - : {} cars successfully found", car);
		return car;
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
	public Page<Car> findAllBySearchRequest(SearchRequest searchRequest) throws SQLException {
		SearchSpecification<Car> specification = new SearchSpecification<>(searchRequest);
		Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
		Page<Car> pageCar = carRepostiry.findAll(specification, pageable);
		log.info("IN findAllFiltered2 - : {} pageCar found", pageCar);
		return pageCar;
	}

	@Override
	@Transactional
	public Car save(Car car) throws SQLException {
		Make make = makeRepository.save(car.getMake());
		Model model = modelRepository.save(new Model(car.getModel().getName(), make));
		List<Category> categories = categoryDao.saveAll(car.getCategories().stream().collect(Collectors.toList()));
		Car carToSave = carRepostiry
				.save(new Car(car.getYear(), make, model, categories.stream().collect(Collectors.toSet())));
		log.info("IN save - car: {} successfully saved", carToSave);
		return car;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Car> findCarsByCategory(Long id) throws SQLException {
		Optional<Car> car = carRepostiry.findCarsByCategory(id);
		log.info("IN findCarsByCategory - : {} cars successfully found", car);
		return car;
	}

	@Override
	public void delete(Long id) throws SQLException {
		carRepostiry.deleteById(id);
		log.info("IN delete (by id) - car with id: {} successfully deleted", id);
	}

	@Override
	public void delete(Car car) throws SQLException {
		carRepostiry.delete(car);
		log.info("IN delete (by object) - car: {} successfully deleted", car);
	}
}
