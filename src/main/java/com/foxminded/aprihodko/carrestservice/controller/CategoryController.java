package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.CategoryDTO;
import com.foxminded.aprihodko.carrestservice.dto.CategoryList;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CategorySpecification;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	ResponseEntity<List<CategoryDTO>> findAll(@RequestParam(required = false) String name, PageOptions pageOptions)
			throws SQLException {
		System.out.println(pageOptions);
		List<Specification<Category>> predicates = Stream
				.of(Optional.ofNullable(name).map(CategorySpecification::hasName)).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());
		return ResponseEntity.ok(CategoryList.fromCategoryList(categoryService.findAllByFilter(predicates, pageOptions)));
	}

	@GetMapping("{id}")
	ResponseEntity<CategoryDTO> findById(@PathVariable(name = "id") Long id) throws SQLException {
		return ResponseEntity.ok(CategoryDTO.fromCategory(categoryService.findById(id).get()));

	}

	@GetMapping("/name/{name}")
	ResponseEntity<CategoryDTO> findByName(@PathVariable(name = "name") String name) throws SQLException {
		return ResponseEntity.ok(CategoryDTO.fromCategory(categoryService.findByUsername(name).get()));
	}

	@PostMapping
	ResponseEntity<CategoryDTO> newCategory(@RequestBody CategoryDTO dto) {
		Category category = CategoryDTO.toCategory(dto);
		Category toDTO = categoryService.save(category);
		return ResponseEntity.ok(CategoryDTO.fromCategory(toDTO));
	}

	@DeleteMapping("{id}")
	void deleteById(@PathVariable Long id) {
		categoryService.delete(id);
	}

	@DeleteMapping()
	void deleteByObject(@RequestBody CategoryDTO dto) {
		categoryService.delete(CategoryDTO.toCategory(dto));
	}
}
