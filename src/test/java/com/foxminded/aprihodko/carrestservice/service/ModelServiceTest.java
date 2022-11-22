package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.ModelDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.ModelSpecification;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.impl.ModelServiceImpl;

@SpringBootTest(classes = ModelServiceImpl.class)
class ModelServiceTest {

	@MockBean
	private ModelRepository modelRepository;

	@MockBean
	private MakeRepository makeRepository;

	@MockBean
	private ModelDao modelDao;

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

	@Test
	void shoudlFindByYear() throws SQLException {
		Make make = new Make(100L, "test");
		List<Model> models = Arrays.asList(new Model(100L, 2020, make));
		when(modelRepository.findByYear(2020)).thenReturn(models);
		List<Model> expeted = modelRepository.findByYear(2020);
		List<Model> actual = modelService.findByYear(2020);
		assertEquals(expeted, actual);
	}

	@Test
	void shouldCreateNew() throws SQLException {
		Make makeToSave = new Make("New");
		when(makeRepository.save(makeToSave)).thenReturn(new Make(100L, "New"));
		Make make = makeRepository.save(makeToSave);
		Model modelToSave = new Model(2022, make);
		when(modelRepository.save(modelToSave)).thenReturn(new Model(100L, 2020, make));
		Model expected = modelRepository.save(modelToSave);
		Model actual = modelService.save(modelToSave);
		assertNotNull(actual.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldUpdate() throws SQLException {
		Make makeToSave = new Make(100L, "New");
		when(makeRepository.save(makeToSave)).thenReturn(makeToSave);
		Make make = makeRepository.save(makeToSave);
		Model modelToSave = new Model(100L, 2022, make);
		when(modelRepository.save(modelToSave)).thenReturn(modelToSave);
		Model expected = modelRepository.save(modelToSave);
		Model actual = modelService.save(modelToSave);
		assertNotNull(actual.getId());
		assertEquals(expected, actual);
	}

//	@Test
	void shouldFindAllFiltered() throws SQLException {
		List<Specification<Model>> modelsSpecifications = Arrays.asList(ModelSpecification.hasMakeId(100L),
				ModelSpecification.hasYear(2022));
		PageOptions pageOptions = new PageOptions();
		List<Model> actual = modelDao.findAllByFilter(modelsSpecifications, pageOptions);
		assertEquals("", actual);
	}

	@Test
	void shouldDeleteById() throws SQLException {
		modelService.delete(100L);
		verify(modelRepository).deleteById(100L);
	}

	@Test
	void shouldDeleteByObject() throws SQLException {
		Make makeToSave = new Make(100L, "New");
		when(makeRepository.save(makeToSave)).thenReturn(makeToSave);
		Make make = makeRepository.save(makeToSave);
		Model modelToSave = new Model(100L, 2022, make);
		when(modelRepository.save(modelToSave)).thenReturn(modelToSave);
		Model modelToDelete = modelRepository.save(modelToSave);
		modelService.delete(modelToDelete);
		Optional<Model> shouldNull = modelRepository.findById(100L);
		assertTrue(shouldNull.isEmpty());
	}
}
