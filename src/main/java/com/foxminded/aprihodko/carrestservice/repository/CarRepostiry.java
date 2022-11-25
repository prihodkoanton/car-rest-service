package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.foxminded.aprihodko.carrestservice.model.Car;

public interface CarRepostiry extends PagingAndSortingRepository<Car, Long>, JpaSpecificationExecutor<Car> {

	List<Car> findByYear(int year) throws SQLException;

	List<Car> findByMakeId(Long id) throws SQLException;

	List<Car> findByModelId(Long id) throws SQLException;

	@Query(value = "select * from cars c LEFT JOIN cars_categories cc ON c.id = cc.category_id where c.id = ?1", nativeQuery = true)
	List<Car> findCarsByCategory(Long id) throws SQLException;
}
