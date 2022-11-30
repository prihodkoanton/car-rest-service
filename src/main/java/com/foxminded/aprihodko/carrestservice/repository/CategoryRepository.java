package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.carrestservice.model.Category;

@Repository
public interface CategoryRepository
		extends PagingAndSortingRepository<Category, Long>, JpaSpecificationExecutor<Category> {

	List<Category> findByName(String name) throws SQLException;

	@Query(value = "select * from categories cs LEFT JOIN cars_categories cc ON cs.id = cc.car_id where cs.id = ?1", nativeQuery = true)
	List<Category> findCategoryByCar(Long id) throws SQLException;
}
