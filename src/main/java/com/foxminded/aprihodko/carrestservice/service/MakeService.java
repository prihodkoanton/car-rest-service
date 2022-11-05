package com.foxminded.aprihodko.carrestservice.service;

import java.sql.SQLException;
import java.util.Optional;

import com.foxminded.aprihodko.carrestservice.model.Make;

public interface MakeService {

	Optional<Make> findByName(String name) throws SQLException;
}
