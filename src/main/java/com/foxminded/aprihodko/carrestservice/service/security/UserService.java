package com.foxminded.aprihodko.carrestservice.service.security;

import java.util.List;
import java.util.Optional;

import com.foxminded.aprihodko.carrestservice.model.security.User;

public interface UserService {

	Optional<User> register(User user);

	List<User> getAll();

	Optional<User> findByUsername(String username);

	Optional<User> findById(Long id);

	void delete(Long id);
}
