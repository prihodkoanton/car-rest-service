package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Make;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MakeRepositoryTest {

    @Autowired
    MakeRepository makeRepository;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
    void shouldFindById() {
        Make expected = new Make(100L, "Audi");
        Make actual = makeRepository.findById(100L).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
    void shouldFindByName() throws SQLException {
        Make expected = new Make(100L, "Audi");
        Make actual = makeRepository.findByName(expected.getName()).get();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
    void shouldCreateNew() throws SQLException {
        Make expected = new Make("Audi");
        Make actual = makeRepository.save(expected);
        assertNotNull(actual.getId());
        expected.setId(actual.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/make_test_data.sql" })
    void shouldDelete() throws SQLException {
        Make make = makeRepository.findById(100L).get();
        assertNotNull(make);
        makeRepository.deleteById(100L);
        Optional<Make> shouldBeNull = makeRepository.findById(100L);
        assertTrue(shouldBeNull.isEmpty());
    }
}
