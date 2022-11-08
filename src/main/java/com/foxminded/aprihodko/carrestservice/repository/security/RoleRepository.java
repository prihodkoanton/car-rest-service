package com.foxminded.aprihodko.carrestservice.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.aprihodko.carrestservice.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);
}
