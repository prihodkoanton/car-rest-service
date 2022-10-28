package com.foxminded.aprihodko.carrestservice.services.impl;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

@Service
public class MakeServiceImpl implements MakeService {

	private final MakeRepository makeRepository;

	public MakeServiceImpl(MakeRepository makeRepository) {
		this.makeRepository = makeRepository;
	}

	@Override
	public Optional<Make> findByName(String name) throws SQLException {
		return makeRepository.findByName(name);
	}
}
