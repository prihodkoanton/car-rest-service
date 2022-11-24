package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
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
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CarSpecification;
import com.foxminded.aprihodko.carrestservice.service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/cars")
@RequiredArgsConstructor
public class CarRestController {

	private final CarService carService;

//	`POST /api/v1/manufacturers/toyota/models/corolla/2001`
//	`GET /api/v1/cars?manufacturer=mercedes&minYear=2005`

//	objectId,Make,Year,Model,Category
//	ZRgPP9dBMm,Audi,2020,Q3,SUV

//	@GetMapping
//	Page<CarDTO> findAll(CarSearchRequest request) throws SQLException {
//		SearchRequest searchRequest = request.asSearchRequest();
//		return carService.findAllByFilter2(searchRequest).map(CarDTO::fromCar);
//	}

	@GetMapping
	ResponseEntity<List<CarDTO>> findAllWithPage(@RequestParam(required = false) Long make_id,
			@RequestParam(required = false) Long model_id,
			com.foxminded.aprihodko.carrestservice.model.PageOptions pageOptions) throws SQLException {

		System.out.println(pageOptions);
		List<Specification<Car>> predicates = Stream
				.of(Optional.ofNullable(make_id).map(CarSpecification::hasMakeId),
						Optional.ofNullable(model_id).map(CarSpecification::hasModelId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		return ResponseEntity.ok(CarList.fromCar(carService.findAllByFilter(predicates, pageOptions)));
	}

	@GetMapping("{id}")
	ResponseEntity<CarDTO> findById(@PathVariable(name = "id") Long id) throws SQLException {
		Car car = carService.findById(id).get();
		return ResponseEntity.ok(CarDTO.fromCar(car));
	}

	@GetMapping("/make/model{year}")
	ResponseEntity<List<CarDTO>> findByYear(@PathVariable(name = "year") int year) throws SQLException {
		List<Car> cars = carService.findByYear(year);
		return ResponseEntity.ok(CarList.fromCar(cars));

	}

	@PostMapping()
	ResponseEntity<CarDTO> findByName(@RequestBody Car car) throws SQLException {
		Car carToSave = carService.save(car);
		return ResponseEntity.ok(CarDTO.fromCar(carToSave));
	}
}
