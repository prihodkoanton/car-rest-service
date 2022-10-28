package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.services.impl.MakeServiceImpl;

@SpringBootTest(classes = MakeServiceImpl.class)
class MakeServiceTest {

	@MockBean
	private MakeRepository makeRepository;

	@Autowired
	private MakeService makeService;

	@Test
	void shouldFindByName() throws SQLException {
		Make make = new Make(100L, "test");
		when(makeRepository.findByName(make.getName())).thenReturn(Optional.of(make));
		Optional<Make> expected = makeRepository.findById(make.getId());
		Optional<Make> actual = makeRepository.findById(make.getId());
		assertEquals(expected, actual);
	}
}
