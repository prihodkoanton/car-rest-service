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

import com.foxminded.aprihodko.carrestservice.BaseDaoTest;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CarSpecification;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CarDaoTest extends BaseDaoTest {

	@Autowired
	private CarDao dao;

	@Autowired
	private MakeDao makeDao;

	@Autowired
	private ModelDao modelDao;

	@Autowired
	private CategoryDao categoryDao;

	@Test
	@Sql(scripts = { "/sql/clear_tables.sql", "/sql/car_test_data.sql" })
	void shouldFindAllByFilter() {
		Make make = new Make(100L, "Audi");
		Category category = new Category(100L, "Sedan");
		Model model = new Model(100L, "test1", make);
		Car car = new Car(100L, 2022, make, model, Set.of(category));
		PageOptions pageOptions = new PageOptions();
		List<Specification<Car>> specifications = Arrays.asList(CarSpecification.hasYear(car.getYear()),
				CarSpecification.hasMakeName(make.getName()), CarSpecification.hasModelName(model.getName()),
				CarSpecification.hasCategoryName(category.getName()));
		List<Car> expected = Arrays.asList(car);
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
		Category category = new Category("Sedan");
		Set<Category> category_set = Set.of(new Category("Sedan"));
		categoryDao.saveAll(Arrays.asList(category));
		List<Car> expected = Arrays.asList(new Car(2022, make1, model1, category_set),
				new Car(2021, make2, model2, category_set));
		List<Car> actual = dao.saveAll(expected);
		assertEquals(expected, actual);
	}
}
