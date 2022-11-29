package com.foxminded.aprihodko.carrestservice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foxminded.aprihodko.carrestservice.service.CarService;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;
import com.foxminded.aprihodko.carrestservice.service.MakeService;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BaseTestForController {

	@MockBean
	CarService carService;

	@MockBean
	MakeService makeService;
	@MockBean
	ModelService modelService;

	@MockBean
	CategoryService categoryService;

	@Autowired
	CarRestController carRestController;

	@Test
	void test() {
		Assertions.assertThat(carRestController).isNot(null);
	}
}
