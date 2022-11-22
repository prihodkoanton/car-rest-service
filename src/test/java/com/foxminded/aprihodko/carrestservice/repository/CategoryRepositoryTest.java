package com.foxminded.aprihodko.carrestservice.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.foxminded.aprihodko.carrestservice.model.Category;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/category_test_data.sql" })
    void findById() {
        Category expected = new Category(100L, "Sedan");
        Category actual = categoryRepository.findById(100L).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/category_test_data.sql" })
    void shouldDeleteById() {
        categoryRepository.deleteById(100L);
        Optional<Category> shouldBeNull = categoryRepository.findById(100L);
        assertTrue(shouldBeNull.isEmpty());
    }

    @Test
    @Sql(scripts = { "/sql/clear_tables.sql", "/sql/category_test_data.sql" })
    void shouldFindCategoryByModels() throws SQLException {
        List<Category> expected = Arrays.asList(new Category(100L, "Sedan"));
        List<Category> actual = categoryRepository.findCategoryByModels(100L);
        assertEquals(expected, actual);
    }
}
