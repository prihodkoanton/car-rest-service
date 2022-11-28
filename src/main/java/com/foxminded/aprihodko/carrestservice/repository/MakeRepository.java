package com.foxminded.aprihodko.carrestservice.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.foxminded.aprihodko.carrestservice.model.Make;

@Repository
public interface MakeRepository extends PagingAndSortingRepository<Make, Long>, JpaSpecificationExecutor<Make> {

	List<Make> findByName(String name) throws SQLException;
}
