package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;

public interface CarService {

	Optional<Car> findById(Long id) throws SQLException;

	List<Car> findByYear(int year) throws SQLException;

	Optional<Car> findByMakeId(Long id) throws SQLException;

	Optional<Car> findByModelId(Long id) throws SQLException;

	List<Car> findAllByFilter(List<Specification<Car>> specifications, PageOptions pageOptions) throws SQLException;

	Page<Car> findAllBySearchRequest(SearchRequest searchRequest) throws SQLException;

	Car save(Car car) throws SQLException;

	Optional<Car> findCarsByCategory(Long id) throws SQLException;

	void delete(Long id) throws SQLException;

	void delete(Car car) throws SQLException;
}
