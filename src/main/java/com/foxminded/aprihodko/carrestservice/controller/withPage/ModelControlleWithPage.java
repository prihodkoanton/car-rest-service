package com.foxminded.aprihodko.carrestservice.controller.withPage;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.ModelSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v2/models")
@RequiredArgsConstructor
public class ModelControlleWithPage {

	private final ModelService modelService;

	@GetMapping
	Page<Model> findAll(ModelSearchRequest request) {
		SearchRequest searchRequest = request.asSerRequest();
		return modelService.findAllFiltered2(searchRequest);
	}
}
