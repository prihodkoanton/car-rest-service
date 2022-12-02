package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarRepostiryTest extends BaseDaoTest {

	@Autowired
	private CarRepostiry repository;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByYear() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, 2022, make, model, category_set);
		List<Car> expected = Arrays.asList(car);
		List<Car> actual = repository.findByYear(car.getYear());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByMakeId() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, 2022, make, model, category_set);
		Optional<Car> expected = Optional.of(car);
		Optional<Car> actual = repository.findByMakeId(car.getMake().getId());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByModelId() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, 2022, make, model, category_set);
		Optional<Car> expected = Optional.of(car);
		Optional<Car> actual = repository.findByMakeId(car.getModel().getId());
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByCategory() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, 2022, make, model, category_set);
		Optional<Car> expected = Optional.of(car);
		Optional<Car> actual = repository.findCarsByCategory(100L);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByYearGreaterThanEqual() throws SQLException {
		Make make = new Make(100L, "Audi");
		Model model = new Model(100L, "test1", make);
		Set<Category> category_set = Set.of(new Category(100L, "Sedan"));
		Car car = new Car(100L, 2022, make, model, category_set);
		List<Car> expected = List.of(car);
		List<Car> actual = repository.findByYearGreaterThanEqual(2022);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindByYearLessThanEqual() throws SQLException {
		Make make1 = new Make(101L, "BMW");
		Model model1 = new Model(101L, "test2", make1);
		Set<Category> category_set1 = Set.of(new Category(101L, "Suv"));
		Car car1 = new Car(101L, 2021, make1, model1, category_set1);
		Make make2 = new Make(102L, "Mercedes");
		Model model2 = new Model(102L, "test3", make2);
		Set<Category> category_set2 = Set.of(new Category(102L, "Pickup"));
		Car car2 = new Car(102L, 2020, make2, model2, category_set2);
		List<Car> expected = List.of(car1, car2);
		List<Car> actual = repository.findByYearLessThanEqual(2021);
		assertEquals(expected, actual);
	}
}
