package com.foxminded.aprihodko.carrestservice.controller;

import static java.util.stream.Collectors.toList;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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

import com.foxminded.aprihodko.carrestservice.dto.ModelDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelList;
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
	ResponseEntity<List<ModelDTO>> findAll(@RequestParam(required = false) Integer year,
			@RequestParam(required = false) Long id, PageOptions pageOptions) {
		System.out.println(pageOptions);
		List<Specification<Model>> predicates = Stream.of(Optional.ofNullable(year).map(ModelSpecification::hasYear))
				.filter(Optional::isPresent).map(Optional::get).collect(toList());
		predicates = Stream.of(Optional.ofNullable(id).map(ModelSpecification::hasMakeId)).filter(Optional::isPresent)
				.map(Optional::get).collect(toList());
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findAllFiltered(predicates, pageOptions)));
	}

	@GetMapping("{year}")
	ResponseEntity<List<ModelDTO>> findByYear(@PathVariable(name = "year") int year) throws SQLException {
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByYear(year)));
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

	@GetMapping("ByMakeId/{id}")
	ResponseEntity<List<ModelDTO>> findByMakeId(@PathVariable(name = "id") Long id) throws SQLException {
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByMakeId(id)));
	}

	@DeleteMapping("{id}")
	void deleteById(Long id) {
		modelService.delete(id);
	}

	@DeleteMapping("{id}")
	void deleteByObject(@RequestBody ModelDTO dto) {
		modelService.delete(ModelDTO.toModel(dto));
	}
}
