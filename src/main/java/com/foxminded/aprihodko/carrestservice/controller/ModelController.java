package com.foxminded.aprihodko.carrestservice.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.ModelSpecification;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

	private final ModelService modelService;

	@GetMapping
	ResponseEntity<List<Model>> findAll(@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Long id, PageOptions pageOptions) {
		System.out.println(pageOptions);
		List<Specification<Model>> predicates = Stream.of(Optional.ofNullable(year).map(ModelSpecification::hasYear))
				.filter(Optional::isPresent).map(Optional::get).collect(toList());
		predicates = Stream.of(Optional.ofNullable(id).map(ModelSpecification::hasMakeId)).filter(Optional::isPresent)
				.map(Optional::get).collect(toList());
		return ResponseEntity.ok(modelService.findAllFiltered(predicates, pageOptions));
	}
}
