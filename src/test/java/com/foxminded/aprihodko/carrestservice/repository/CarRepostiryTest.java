package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepostiryTest {

	@Autowired
	private CarRepostiry repository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByMakeId() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, make, model, category_set);
		List<Car> expected = Arrays.asList(car);
		List<Car> actual = repository.findByMakeId(car.getMake().getId());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByModelId() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, make, model, category_set);
		List<Car> expected = Arrays.asList(car);
		List<Car> actual = repository.findByMakeId(car.getModel().getId());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByCategory() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, make, model, category_set);
		List<Car> expected = Arrays.asList(car);
		List<Car> actual = repository.findCarsByCategory(100L);
		assertEquals(expected, actual);
	}
}
