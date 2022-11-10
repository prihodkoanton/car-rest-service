package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.carrestservice.model.Make;

@Repository
public interface MakeRepository
		extends JpaRepository<Make, Long>, PagingAndSortingRepository<Make, Long>, JpaSpecificationExecutor<Make> {

	Optional<Make> findByName(String name) throws SQLException;
}
