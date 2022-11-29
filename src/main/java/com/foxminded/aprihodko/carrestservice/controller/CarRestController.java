package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.dto.CarList;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CarSpecification;
import com.foxminded.aprihodko.carrestservice.service.CarService;
import com.foxminded.aprihodko.carrestservice.service.CategoryService;
import com.foxminded.aprihodko.carrestservice.service.MakeService;
import com.foxminded.aprihodko.carrestservice.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CarRestController {

	private final CarService carService;
	private final MakeService makeService;
	private final ModelService modelService;
	private final CategoryService categoryService;

	@GetMapping
	ResponseEntity<List<CarDTO>> findAllWithPage(@RequestParam(required = false) String make,
			@RequestParam(required = false) String model, @RequestParam(required = false) Integer year,
			@RequestParam(required = false) String category, PageOptions pageOptions) throws SQLException {

		System.out.println(pageOptions);
		List<Specification<Car>> makeIds = hasMakeName(make);
		List<Specification<Car>> modelIds = hasModelName(model);
		List<Specification<Car>> categories = hasCateoryName(category);
		List<Specification<Car>> predicates = new ArrayList<>();
		predicates = Stream.of(Optional.ofNullable(year).map(CarSpecification::hasYear)).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());
		predicates.addAll(makeIds);
		predicates.addAll(modelIds);
		predicates.addAll(categories);

		return ResponseEntity.ok(CarList.fromCar(carService.findAllByFilter(predicates, pageOptions)));
	}

	private List<Specification<Car>> hasMakeName(String name) throws SQLException {
		List<Long> makeIds = makeService.findByName(name).stream().map(Make::getId).collect(Collectors.toList());
		List<Specification<Car>> result = makeIds.stream().map(CarSpecification::hasMakeId).collect(Collectors.toList());
		return result;
	}

	private List<Specification<Car>> hasModelName(String name) throws SQLException {
		List<Model> models = modelService.findByName(name);
		List<Long> modelIds = models.stream().map(Model::getId).collect(Collectors.toList());
		List<Specification<Car>> result = modelIds.stream().map(CarSpecification::hasModelId)
				.collect(Collectors.toList());
		return result;
	}

	private List<Specification<Car>> hasCateoryName(String name) throws SQLException {
		List<Category> categories = categoryService.findByUsername(name);
		List<Long> categoryIds = categories.stream().map(Category::getId).collect(Collectors.toList());
		List<Specification<Car>> result = categoryIds.stream().map(CarSpecification::hasCategory)
				.collect(Collectors.toList());
		return result;
	}

	@PostMapping()
	ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) throws SQLException {
		Car carToSave = carService.save(CarDTO.toCar(dto));
		return ResponseEntity.ok(CarDTO.fromCar(carToSave));
	}

	@DeleteMapping()
	void deleteByObject(@RequestBody CarDTO dto) throws SQLException {
		carService.delete(CarDTO.toCar(dto));
	}

	@DeleteMapping({ "id" })
	void deleteById(@PathVariable Long id) throws SQLException {
		carService.delete(id);
	}
}
