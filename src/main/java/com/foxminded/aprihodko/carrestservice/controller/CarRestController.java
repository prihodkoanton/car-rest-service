package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
		List<Specification<Car>> predicates = new ArrayList<>();
		predicates = Stream
				.of(Optional.ofNullable(year).map(CarSpecification::hasYear),
						Optional.ofNullable(category).map(CarSpecification::hasCategoryName),
						Optional.ofNullable(model).map(CarSpecification::hasModelName),
						Optional.ofNullable(make).map(CarSpecification::hasMakeName))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		return ResponseEntity.ok(CarList.fromCar(carService.findAllByFilter(predicates, pageOptions)));
	}

	@PostMapping()
	ResponseEntity<CarDTO> save(@RequestBody CarDTO dto) throws SQLException {
		Car carToSave = carService.save(CarDTO.toCar(dto));
		return ResponseEntity.ok(CarDTO.fromCar(carToSave));
	}

	@PostMapping("/make/{make_name}/model/{model_name}/category/{category_name}/year/{year}")
	ResponseEntity<CarDTO> createNew(@PathVariable String make_name, @PathVariable String model_name,
			@PathVariable String category_name, @PathVariable int year) throws SQLException {
		Make make = makeService.save(new Make(make_name));
		Model model = modelService.save(new Model(model_name, make));
		Category category = categoryService.save(new Category(category_name));
		Car carToSave = new Car(year, make, model, Set.of(category));
		Car result = carService.save(carToSave);
		return ResponseEntity.ok(CarDTO.fromCar(result));
	}

	@DeleteMapping("delete")
	void deleteByObject(@RequestBody CarDTO dto) throws SQLException {
		carService.delete(CarDTO.toCar(dto));
	}

	@DeleteMapping("delete/{id}")
	void deleteById(@PathVariable Long id) throws SQLException {
		carService.delete(id);
	}
}
