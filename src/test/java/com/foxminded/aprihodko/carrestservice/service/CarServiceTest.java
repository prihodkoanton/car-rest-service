package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.CarSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.repository.CarRepostiry;
import com.foxminded.aprihodko.carrestservice.repository.dao.CarDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.impl.CarServiceImpl;

@SpringBootTest(classes = CarServiceImpl.class)
class CarServiceTest {

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
				List.of(new Car(1L, "1", 2020, make, model, categories), new Car(2L, "2", 2020, make, model, categories)));

		when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expected);

		Page<CarDTO> actual = service.findAllByFilter2(searchRequest);
		assertFalse(actual.isEmpty());
	}

}
