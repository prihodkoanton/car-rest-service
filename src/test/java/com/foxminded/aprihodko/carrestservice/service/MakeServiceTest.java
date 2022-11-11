package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.MakeDao;
import com.foxminded.aprihodko.carrestservice.service.impl.MakeServiceImpl;

@SpringBootTest(classes = MakeServiceImpl.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MakeServiceTest {

	@MockBean
	private MakeRepository makeRepository;

	@MockBean
	private MakeDao makeDao;

	@Autowired
	private MakeService makeService;

	@Test
	void shouldFindByName() throws SQLException {
		Make make = new Make(100L, "test");
		when(makeRepository.findByName(make.getName())).thenReturn(Optional.of(make));
		Optional<Make> expected = makeRepository.findByName(make.getName());
		Optional<Make> actual = makeService.findByName(make.getName());
		assertEquals(expected, actual);
	}
}
