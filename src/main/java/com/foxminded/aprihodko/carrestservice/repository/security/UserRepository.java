package com.foxminded.aprihodko.carrestservice.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.aprihodko.carrestservice.model.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
