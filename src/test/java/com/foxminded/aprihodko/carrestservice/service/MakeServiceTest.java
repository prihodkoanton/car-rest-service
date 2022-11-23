package com.foxminded.aprihodko.carrestservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.MakeRepository;
import com.foxminded.aprihodko.carrestservice.repository.dao.MakeDao;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.MakeSpecification;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.impl.MakeServiceImpl;

@SpringBootTest(classes = MakeServiceImpl.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MakeServiceTest {

    @MockBean
    private MakeRepository makeRepository;

    @MockBean
    private MakeDao makeDao;

    @Autowired
    private MakeService makeService;

    @Test
    void shouldFindByName() throws SQLException {
        Make make = new Make(100L, "test");
        when(makeRepository.findByName(make.getName())).thenReturn(Optional.of(make));
        Optional<Make> expected = makeRepository.findByName(make.getName());
        Optional<Make> actual = makeService.findByName(make.getName());
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() throws SQLException {
        Make make = new Make(100L, "test");
        when(makeRepository.findByName(make.getName())).thenReturn(Optional.of(make));
        Optional<Make> expected = makeRepository.findByName(make.getName());
        Optional<Make> actual = makeService.findByName(make.getName());
        assertEquals(expected, actual);
    }

    @Test
    void shouldCreateNew() throws SQLException {
        Make makeToSave = new Make("New");
        when(makeRepository.save(makeToSave)).thenReturn(new Make(100L, "New"));
        Make expected = makeRepository.save(makeToSave);
        Make actual = makeService.save(makeToSave);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() throws SQLException {
        Make make = new Make(100L, "New");
        when(makeRepository.save(make)).thenReturn(make);
        Make expected = makeRepository.save(make);
        Make actual = makeService.save(make);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllByFilter() throws SQLException {
        List<Specification<Make>> makesSpecifications = Arrays.asList(MakeSpecification.hasName("Test"));
        PageOptions pageOptions = new PageOptions();

    }

    @Test
    void shouldFindAllByFilter2() throws SQLException {

    }

    @Test
    void shouldDeleteById() throws SQLException {

    }

    @Test
    void shouldDeleteByObject() throws SQLException {

    }
}
