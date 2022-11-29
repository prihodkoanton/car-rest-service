package com.foxminded.aprihodko.carrestservice.controller.withPage;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.CategorySearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v2/categories")
@RequiredArgsConstructor
public class CategoryControlleWithPage {

	private final CategoryService service;

	@GetMapping
	Page<Category> findAll(CategorySearchRequest request) {
		SearchRequest searchRequest = request.asSearchRequest();
		return service.findAllBySearchRequest(searchRequest);
	}
}
