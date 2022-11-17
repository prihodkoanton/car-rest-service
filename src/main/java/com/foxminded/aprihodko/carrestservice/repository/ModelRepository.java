package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.carrestservice.model.Model;

@Repository
public interface ModelRepository
		extends JpaRepository<Model, Long>, PagingAndSortingRepository<Model, Long>, JpaSpecificationExecutor<Model> {

	List<Model> findByYear(int year) throws SQLException;

	List<Model> findByMakeId(Long id) throws SQLException;
}
