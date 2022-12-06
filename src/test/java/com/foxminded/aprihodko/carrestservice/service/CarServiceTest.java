package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.CarSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.CarRepostiry;
import com.foxminded.aprihodko.carrestservice.repository.dao.CarDao;
import com.foxminded.aprihodko.carrestservice.service.impl.CarServiceImpl;

@SpringBootTest(classes = CarServiceImpl.class)
class CarServiceTest extends BaseDaoTest {

	@MockBean
	private CarRepostiry repository;

	@MockBean
	private CarDao dao;

	@Autowired
	private CarService service;

	@Test
	void shouldFindByParameters() throws SQLException {
		CarSearchRequest carSearchRequest = new CarSearchRequest();

		Make make = new Make(150L, "make");
		Model model = new Model(150L, "model", make);
		Set<Category> categories = Set.of(new Category(150L, "category"));

		SearchRequest searchRequest = carSearchRequest.asSearchRequest();

		Page<Car> expected = new PageImpl<>(
				List.of(new Car(1L, 2020, make, model, categories), new Car(2L, 2020, make, model, categories)));

		when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expected);

		Page<Car> actual = service.findAllBySearchRequest(searchRequest);
		assertFalse(actual.isEmpty());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByYear() throws SQLException {
		List<Car> cars = Arrays.asList(new Car(1L, 2022, new Make(1L, "test1"),
				new Model(1L, "test1", new Make(1L, "test")), Set.of(new Category(1L, "test1"))));
		when(repository.findByYear(2022)).thenReturn(cars);
		List<Car> expected = repository.findByYear(2022);
		List<Car> actual = service.findByYear(2022);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindById() throws SQLException {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(repository.findById(car.getId())).thenReturn(Optional.of(car));
		Optional<Car> expected = repository.findById(car.getId());
		Optional<Car> actual = service.findById(car.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByMakeId() throws SQLException {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(repository.findByMakeId(car.getMake().getId())).thenReturn(Optional.of(car));
		Optional<Car> expected = repository.findByMakeId(car.getMake().getId());
		Optional<Car> actual = service.findByMakeId(car.getMake().getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindByModelId() throws SQLException {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(repository.findByModelId(car.getModel().getId())).thenReturn(Optional.of(car));
		Optional<Car> expected = repository.findByModelId(car.getMake().getId());
		Optional<Car> actual = service.findByModelId(car.getMake().getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindCarsByCategory() throws SQLException {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(repository.findCarsByCategory(1L)).thenReturn(Optional.of(car));
		Optional<Car> expected = repository.findCarsByCategory(1L);
		Optional<Car> actual = service.findCarsByCategory(1L);
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		service.delete(1L);
		verify(repository).deleteById(1L);
	}

	@Test
	void shouldDeleteByObject() throws SQLException {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(repository.save(car)).thenReturn(car);
		Car carToDelete = repository.save(car);
		service.delete(carToDelete);
		Optional<Car> shouldBeNull = repository.findById(carToDelete.getId());
		assertTrue(shouldBeNull.isEmpty());
	}
}
