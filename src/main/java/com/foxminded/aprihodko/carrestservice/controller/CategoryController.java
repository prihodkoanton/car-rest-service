package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CategorySpecifications;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	ResponseEntity<List<Category>> findAll(@RequestParam(required = false) String name, PageOptions pageOptions)
			throws SQLException {
		System.out.println(pageOptions);
		List<Specification<Category>> predicates = Stream
				.of(Optional.ofNullable(name).map(CategorySpecifications::hasName)).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());
		return ResponseEntity.ok(categoryService.findAllFiltered(predicates, pageOptions));
	}
}
