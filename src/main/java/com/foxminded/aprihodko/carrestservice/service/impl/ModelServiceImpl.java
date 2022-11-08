package com.foxminded.aprihodko.carrestservice.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService {

	private final ModelRepository modelRepository;

	public ModelServiceImpl(ModelRepository modelRepository) {
		this.modelRepository = modelRepository;
	}

	@Override
	public List<Model> findByYear(int year) throws SQLException {
		return modelRepository.findByYear(year);
	}

	@Override
	public List<Model> findByMakeId(Long id) throws SQLException {
		return modelRepository.findByMakeId(id);
	}
}
