package com.foxminded.aprihodko.carrestservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.security.config.SecurityConfig;
import com.foxminded.aprihodko.carrestservice.service.CarService;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;
import com.foxminded.aprihodko.carrestservice.service.MakeService;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

@WebMvcTest(CarRestController.class)
@Import(SecurityConfig.class)
class CarRestControllerTest {

	@MockBean
	CarService carService;

	@MockBean
	MakeService makeService;

	@MockBean
	ModelService modelService;

	@MockBean
	CategoryService categoryService;

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithMockUser
	void shouldFindAllByFilter() throws Exception {
		List<Car> cars = Arrays.asList(new Car(1L, 2022, new Make(1L, "test1"),
				new Model(1L, "test1", new Make(1L, "test")), Set.of(new Category(1L, "test1"))));
		List<Specification<Car>> specifications = new ArrayList<>();
		PageOptions pageOptions = new PageOptions();
		when(carService.findAllByFilter(specifications, pageOptions)).thenReturn(cars);
		mockMvc.perform(get("/api/v1/cars")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	@WithMockUser
	void shouldCreateNew() throws Exception {
		Car car = new Car(1L, 2022, new Make(1L, "test1"), new Model(1L, "test1", new Make(1L, "test")),
				Set.of(new Category(1L, "test1")));
		when(carService.save(car)).thenReturn(car);
		mockMvc.perform(post("/api/v1/cars"))
				.andExpect(status().is(400));/* .andExpect(jsonPath("$", Matchers.hasSize(1))); */
	}
}
