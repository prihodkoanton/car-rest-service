package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.MakeDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelDTO;
import com.foxminded.aprihodko.carrestservice.dto.ModelList;
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
	Page<ModelDTO> findAll(ModelSearchRequest request) {
		SearchRequest searchRequest = request.asSerRequest();
		Page<ModelDTO> result = modelService.findAllFiltered2(searchRequest).map(ModelDTO::fromModel);
		return result;
	}

	@GetMapping("/make/{name}")
	ResponseEntity<MakeDTO> findMakeByName(@PathVariable(name = "name") String name) throws SQLException {
		return ResponseEntity.ok(MakeDTO.fromMake(makeService.findByName(name).get()));
	}

	@GetMapping("/models/{name}")
	ResponseEntity<List<ModelDTO>> findModelByName(@PathVariable(name = "name") int year) throws SQLException {
		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByYear(year)));
	}
}
