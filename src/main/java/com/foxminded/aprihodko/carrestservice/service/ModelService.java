package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.List;

import com.foxminded.aprihodko.carrestservice.model.Model;

public interface ModelService {

	List<Model> findByYear(int year) throws SQLException;

	List<Model> findByMakeId(Long id) throws SQLException;
}
