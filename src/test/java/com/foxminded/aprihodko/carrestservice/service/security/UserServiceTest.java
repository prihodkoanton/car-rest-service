package com.foxminded.aprihodko.carrestservice.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.security.User;
import com.foxminded.aprihodko.carrestservice.repository.security.UserRepository;
import com.foxminded.aprihodko.carrestservice.service.security.impl.UserServiceImpl;

@SpringBootTest(classes = { UserServiceImpl.class })
class UserServiceTest extends BaseDaoTest {

	@Mock
	UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	void shouldFoundByUsername() {
		User user = new User();
		user.setId(100L);
		user.setUsername("Anton");
		user.setPassword("12345678");
		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
		Optional<User> expected = userRepository.findByUsername(user.getUsername());
		Optional<User> actual = userService.findByUsername(user.getUsername());
		assertEquals(expected, actual);
	}
}
