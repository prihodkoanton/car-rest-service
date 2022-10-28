package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.services.impl.ModelServiceImpl;

@SpringBootTest(classes = ModelServiceImpl.class)
class ModelServiceTest {

	@MockBean
	private ModelRepository modelRepository;

	@Autowired
	private ModelService modelService;

	@Test
	void shoudlFindByMakeId() throws SQLException {
		Make make = new Make(100L, "test");
		List<Model> models = Arrays.asList(new Model(100L, 2020, make));
		when(modelRepository.findByMakeId(make.getId())).thenReturn(models);
		List<Model> expeted = modelRepository.findByMakeId(make.getId());
		List<Model> actual = modelService.findByMakeId(make.getId());
		assertEquals(expeted, actual);
	}

}
