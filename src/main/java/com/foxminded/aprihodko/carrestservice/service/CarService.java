package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

public interface CarService {

	Optional<CarDTO> findById(Long id) throws SQLException;

	List<CarDTO> findByYear(int year) throws SQLException;

	Optional<CarDTO> findByName(String name) throws SQLException;

	List<CarDTO> findByMakeId(Long id) throws SQLException;

	List<CarDTO> findByModelId(Long id) throws SQLException;

	List<CarDTO> findAllByFilter(List<Specification<Car>> specifications, PageOptions pageOptions) throws SQLException;

	Page<CarDTO> findAllByFilter2(SearchRequest searchRequest) throws SQLException;

	List<CarDTO> findAllFilteredAsListDTO(SearchRequest searchRequest) throws SQLException;

	CarDTO save(Car car) throws SQLException;

	List<CarDTO> findCarsByCategory(Long id) throws SQLException;

	void delete(Long id) throws SQLException;

	void delete(Car car) throws SQLException;
}
