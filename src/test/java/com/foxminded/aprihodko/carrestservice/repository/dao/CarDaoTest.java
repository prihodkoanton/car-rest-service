package com.foxminded.aprihodko.carrestservice.repository.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CarSpecification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CarDaoTest {

	@Autowired
	private CarDao dao;

	@Autowired
	private MakeDao makeDao;

	@Autowired
	private ModelDao modelDao;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindAllByFilter() {
		Make make = new Make(100L, "Audi");
		Category category = new Category(100L, "Sedan");
		Set<Category> category_set = Set.of(category);
		PageOptions pageOptions = new PageOptions();
		List<Specification<Car>> specifications = Arrays.asList(CarSpecification.hasYear(2022),
				CarSpecification.hasMakeId(100L), CarSpecification.hasModelId(100L));
		List<Car> expected = Arrays.asList(new Car(100L, 2022, make, new Model(100L, "test1", make), category_set));
		List<Car> actual = dao.findAllByFilter(specifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldSaveAll() {
		Make make1 = new Make("Audi");
		Make make2 = new Make("BMW");
		List<Make> makesToSave = Arrays.asList(make1, make2);
		makeDao.saveAll(makesToSave);
		Model model1 = new Model("test1", make1);
		Model model2 = new Model("test2", make2);
		List<Model> modelToSave = Arrays.asList(model1, model2);
		modelDao.saveAll(modelToSave);
		Set<Category> category_set = Set.of(new Category("Sedan"));
		List<Car> expected = Arrays.asList(new Car(2022, make1, model1, category_set),
				new Car(2021, make2, model2, category_set));
		List<Car> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}
}
