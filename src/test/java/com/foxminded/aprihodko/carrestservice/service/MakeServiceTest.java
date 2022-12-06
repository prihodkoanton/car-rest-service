package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.MakeSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.MakeDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.MakeSpecification;
import com.foxminded.aprihodko.carrestservice.service.impl.MakeServiceImpl;

@SpringBootTest(classes = MakeServiceImpl.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MakeServiceTest extends BaseDaoTest {

	@MockBean
	private MakeRepository makeRepository;

	@MockBean
	private MakeDao makeDao;

	@Autowired
	private MakeService makeService;

	@Test
	void shouldFindByName() throws SQLException {
		List<Make> makes = Arrays.asList(new Make(100L, "test"), new Make(101L, "test"));
		when(makeRepository.findByName("test")).thenReturn(makes);
		List<Make> expected = makeRepository.findByName("test");
		List<Make> actual = makeService.findByName("test");
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindById() throws SQLException {
		Make make = new Make(100L, "test");
		when(makeRepository.findById(make.getId())).thenReturn(Optional.of(make));
		Optional<Make> expected = makeRepository.findById(make.getId());
		Optional<Make> actual = makeService.findById(make.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldCreateNew() throws SQLException {
		Make makeToSave = new Make("New");
		when(makeRepository.save(makeToSave)).thenReturn(new Make(100L, "New"));
		Make expected = makeRepository.save(makeToSave);
		Make actual = makeService.save(makeToSave);
		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdate() throws SQLException {
		Make make = new Make(100L, "New");
		when(makeRepository.save(make)).thenReturn(make);
		Make expected = makeRepository.save(make);
		Make actual = makeService.save(make);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAllByFilter() throws SQLException {
		List<Specification<Make>> makesSpecifications = Arrays.asList(MakeSpecification.hasName("Test"));
		PageOptions pageOptions = new PageOptions();
		List<Make> makes = Arrays.asList(new Make(100L, "test"), new Make(102L, "test2"));
		when(makeDao.findAllByFilter(makesSpecifications, pageOptions)).thenReturn(makes);
		List<Make> expected = makeDao.findAllByFilter(makesSpecifications, pageOptions);
		List<Make> actual = makeService.findAllByFilter(makesSpecifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAllBySearchRequest() throws SQLException {
		MakeSearchRequest makeSearchRequest = new MakeSearchRequest();
		SearchRequest searchRequest = makeSearchRequest.asSearchRequest();
		Page<Make> expeted = new PageImpl<>(List.of(new Make(100L, "test")));
		when(makeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expeted);
		Page<Make> actual = makeService.findAllBySearchRequest(searchRequest);
		assertFalse(actual.isEmpty());
		assertEquals(expeted, actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		Make make = new Make("test1");
		when(makeRepository.save(make)).thenReturn(make);
		Make makeToDelete = makeRepository.save(make);
		makeService.delete(make.getId());
		Optional<Make> shouldBeNull = makeRepository.findById(makeToDelete.getId());
		assertTrue(shouldBeNull.isEmpty());
	}

	@Test
	void shouldDeleteByObject() throws SQLException {
		Make make = new Make("test1");
		when(makeRepository.save(make)).thenReturn(make);
		Make makeToDelete = makeRepository.save(make);
		makeService.delete(makeToDelete);
		Optional<Make> shouldBeNull = makeRepository.findById(makeToDelete.getId());
		assertTrue(shouldBeNull.isEmpty());
	}
}
