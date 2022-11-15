package com.foxminded.aprihodko.carrestservice.controller;

import static java.util.stream.Collectors.toList;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.MakeSpecification;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/makes")
@RequiredArgsConstructor
public class MakeController {

	private final MakeService makeService;

	ResponseEntity<List<Make>> findAll(@RequestParam(required = false) String name, PageOptions pageOptions)
			throws SQLException {
		System.out.println(pageOptions);
		List<Specification<Make>> predicates = Stream.of(Optional.ofNullable(name).map(MakeSpecification::hasName))
				.filter(Optional::isPresent).map(Optional::get).collect(toList());
		return ResponseEntity.ok(makeService.findAllFiltered(predicates, pageOptions));
	}
}
