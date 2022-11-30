package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.ModelDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelList;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.ModelSpecification;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

	private final ModelService modelService;

	@GetMapping
	ResponseEntity<List<ModelDTO>> findAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long id, PageOptions pageOptions) {
		System.out.println(pageOptions);
		List<Specification<Model>> predicates = Stream.of(Optional.ofNullable(name).map(ModelSpecification::hasName))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
		predicates = Stream.of(Optional.ofNullable(id).map(ModelSpecification::hasMakeId)).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findAllByFilter(predicates, pageOptions)));
	}

	@GetMapping("{id}")
	ResponseEntity<ModelDTO> findById(@PathVariable(name = "id") Long id) throws SQLException {
		return ResponseEntity.ok(ModelDTO.fromModel(modelService.findById(id).get()));
	}

	@PostMapping
	ResponseEntity<ModelDTO> newModel(ModelDTO dto) {
		Model model = ModelDTO.toModel(dto);
		Model toDTO = modelService.save(model);
		return ResponseEntity.ok(ModelDTO.fromModel(toDTO));
	}

	@GetMapping("byMakeId/{id}")
	ResponseEntity<List<ModelDTO>> findByMakeId(@PathVariable(name = "id") Long id) throws SQLException {
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByMakeId(id)));
	}

	@DeleteMapping("{id}")
	void deleteById(Long id) {
		modelService.delete(id);
	}

	@DeleteMapping
	void deleteByObject(@RequestBody ModelDTO dto) {
		modelService.delete(ModelDTO.toModel(dto));
	}
}
