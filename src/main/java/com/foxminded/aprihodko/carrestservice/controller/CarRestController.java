package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.CategoryDTO;
import com.foxminded.aprihodko.carrestservice.dto.MakeDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelList;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.CategorySearchRequest;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.MakeSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.ModelSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;
import com.foxminded.aprihodko.carrestservice.service.MakeService;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/cars")
@RequiredArgsConstructor
public class CarRestController {

	private final MakeService makeService;
	private final ModelService modelService;
	private final CategoryService categoryService;

//	`POST /api/v1/manufacturers/toyota/models/corolla/2001`
//	`GET /api/v1/cars?manufacturer=mercedes&minYear=2005`

//	objectId,Make,Year,Model,Category
//	ZRgPP9dBMm,Audi,2020,Q3,SUV

	@GetMapping()
	Page<ModelDTO> findAllModels(ModelSearchRequest request) {
		SearchRequest searchRequest = request.asSerRequest();
		Page<ModelDTO> result = modelService.findAllFiltered2(searchRequest).map(ModelDTO::fromModel);
		return result;
	}

	@GetMapping("{year}")
	ResponseEntity<List<ModelDTO>> findModelByName(@PathVariable(name = "year") int year) throws SQLException {
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByYear(year)));
	}

// NEED TO DO
	@PostMapping
	ResponseEntity<ModelDTO> newModel(ModelDTO dto) {
		Model model = ModelDTO.toModel(dto);
		Model toDTO = modelService.save(model);
		return ResponseEntity.ok(ModelDTO.fromModel(toDTO));
	}
// NEER OR NOT?
//	@GetMapping("{id}")
//	ResponseEntity<List<ModelDTO>> findModelsByMakeId(@PathVariable(name = "id") Long id) throws SQLException {
//		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByMakeId(id)));
//	}

	@DeleteMapping("{id}")
	void deleteModelById(Long id) {
		modelService.delete(id);
	}

	@DeleteMapping
	void deleteModelByObject(@RequestBody ModelDTO dto) {
		modelService.delete(ModelDTO.toModel(dto));
	}

	@GetMapping("/makes")
	Page<MakeDTO> findAllMakes(MakeSearchRequest request) throws SQLException {
		SearchRequest searchRequest = request.asSearchRequest();
		Page<MakeDTO> result = makeService.findAllFiltered2(searchRequest).map(MakeDTO::fromMake);
		return result;
	}

	@GetMapping("/makes/{name}")
	ResponseEntity<MakeDTO> findMakeByName(@PathVariable(name = "name") String name) throws SQLException {
		return ResponseEntity.ok(MakeDTO.fromMake(makeService.findByName(name).get()));
	}

	@PostMapping("/makes")
	ResponseEntity<MakeDTO> newMake(@RequestBody MakeDTO dto) {
		Make make = MakeDTO.toMake(dto);
		Make toDTO = makeService.save(make);
		return ResponseEntity.ok(MakeDTO.fromMake(toDTO));
	}

	@DeleteMapping("/makes/{id}")
	void deleteMakeById(@PathVariable Long id) {
		makeService.delete(id);
	}

	@DeleteMapping("/makes")
	void deleteMakeByObject(@RequestBody MakeDTO dto) {
		makeService.delete(MakeDTO.toMake(dto));
	}

	@GetMapping("/makes/categories")
	Page<Category> findAllCategories(CategorySearchRequest request) {
		SearchRequest searchRequest = request.asSearchRequest();
		return categoryService.findAllFiltered2(searchRequest);
	}

	@GetMapping("/makes/categories/{name}")
	ResponseEntity<CategoryDTO> findCategoryByName(@PathVariable(name = "name") String name) throws SQLException {
		return ResponseEntity.ok(CategoryDTO.fromCategory(categoryService.findByUsername(name).get()));
	}

	@PostMapping("/makes/categories")
	ResponseEntity<CategoryDTO> newCategory(@RequestBody CategoryDTO dto) {
		Category category = CategoryDTO.toCategory(dto);
		Category toDTO = categoryService.save(category);
		return ResponseEntity.ok(CategoryDTO.fromCategory(toDTO));
	}

	@DeleteMapping("/makes/categories/{id}")
	void deleteById(@PathVariable Long id) {
		categoryService.delete(id);
	}

	@DeleteMapping("/makes/categories/")
	void deleteByObject(@RequestBody CategoryDTO dto) {
		categoryService.delete(CategoryDTO.toCategory(dto));
	}
}
