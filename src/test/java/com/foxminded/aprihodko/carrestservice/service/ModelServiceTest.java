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
import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.ModelRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.ModelDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.ModelSpecification;
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
	void shoudlFindById() throws SQLException {
		Make make = new Make(100L, "test");
		Model model = new Model(100L, "test1", make);
		when(modelRepository.findById(model.getId())).thenReturn(Optional.of(model));
		Optional<Model> expected = modelRepository.findById(model.getId());
		Optional<Model> actual = modelService.findById(model.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shoudlFindByMakeId() throws SQLException {
		Make make = new Make(100L, "test");
		List<Model> models = Arrays.asList(new Model(100L, "test", make));
		when(modelRepository.findByMakeId(make.getId())).thenReturn(models);
		List<Model> expeted = modelRepository.findByMakeId(make.getId());
		List<Model> actual = modelService.findByMakeId(make.getId());
		assertEquals(expeted, actual);
	}

	@Test
	void shouldCreateNew() throws SQLException {
		Make makeToSave = new Make("New");
		when(makeRepository.save(makeToSave)).thenReturn(new Make(100L, "New"));
		Make make = makeRepository.save(makeToSave);
		Model modelToSave = new Model("test", make);
		when(modelRepository.save(modelToSave)).thenReturn(new Model(100L, "test", make));
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
		Model modelToSave = new Model(100L, "test", make);
		when(modelRepository.save(modelToSave)).thenReturn(modelToSave);
		Model expected = modelRepository.save(modelToSave);
		Model actual = modelService.save(modelToSave);
		assertNotNull(actual.getId());
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAllFiltered() throws SQLException {
		List<Specification<Model>> modelsSpecifications = Arrays.asList(ModelSpecification.hasMakeId(100L));
		PageOptions pageOptions = new PageOptions();
		List<Model> models = Arrays.asList(new Model(100L, "test", new Make(100L, "Test")));
		when(modelDao.findAllByFilter(modelsSpecifications, pageOptions)).thenReturn(models);
		List<Model> expected = modelDao.findAllByFilter(modelsSpecifications, pageOptions);
		List<Model> actual = modelService.findAllByFilter(modelsSpecifications, pageOptions);
		assertEquals(expected, actual);
	}

	@Test
	void shouldFindAllFiltered2() throws SQLException {
		List<Specification<Model>> modelsSpecifications = Arrays.asList(ModelSpecification.hasMakeId(100L));
		PageOptions pageOptions = new PageOptions();
		List<Model> models = Arrays.asList();
		when(modelDao.findAllByFilter(modelsSpecifications, pageOptions)).thenReturn(models);
		List<Model> expected = modelDao.findAllByFilter(modelsSpecifications, pageOptions);
		List<Model> actual = modelService.findAllByFilter(modelsSpecifications, pageOptions);
		assertEquals(expected, actual);
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
		Model modelToSave = new Model(100L, "test", make);
		when(modelRepository.save(modelToSave)).thenReturn(modelToSave);
		Model modelToDelete = modelRepository.save(modelToSave);
		modelService.delete(modelToDelete);
		Optional<Model> shouldNull = modelRepository.findById(100L);
		assertTrue(shouldNull.isEmpty());
	}
}
