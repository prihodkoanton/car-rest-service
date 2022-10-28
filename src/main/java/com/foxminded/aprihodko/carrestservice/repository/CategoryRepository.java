package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.carrestservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name) throws SQLException;

	List<Category> findCategoryByModels(Long id) throws SQLException;
}
