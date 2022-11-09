package com.foxminded.aprihodko.carrestservice.service.security.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.security.Role;
import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.repository.security.RoleRepository;
import com.foxminded.aprihodko.carrestservice.repository.security.UserRepository;
import com.foxminded.aprihodko.carrestservice.service.security.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public Optional<User> register(User user) {
		Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow();
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(roleUser);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(user.getRoles());
		user.setStatus(user.getStatus());

		User registeredUser = userRepository.save(user);

		log.info("IN register - user: {} successfully registered", registeredUser);
		return Optional.of(registeredUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAll() {
		List<User> result = userRepository.findAll();
		log.info("IN getAll - {} users found", result.size());
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByUsername(String username) {
		Optional<User> result = userRepository.findByUsername(username);
		log.info("IN findByUsername - user: {} found by name: {}", result, username);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		Optional<User> result = userRepository.findById(id);
		if (result.isEmpty()) {
			log.warn("IN findById - no user found by id: {}", id);
			return null;
		}
		log.info("IN findById - user: {} found by id: {}", result);
		return result;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
		log.info("IN delete - user with id: {} successfully deleted");
	}
}
