package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.dto.CarList;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.CarSpecification;
import com.foxminded.aprihodko.carrestservice.service.CarService;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/cars")
@RequiredArgsConstructor
public class CarRestController {

	private final CarService carService;
	private final MakeService makeService;

//	`POST /api/v1/manufacturers/toyota/models/corolla/2001`
//	`GET /api/v1/cars?manufacturer=mercedes&minYear=2005`

//	objectId,Make,Year,Model,Category
//	ZRgPP9dBMm,Audi,2020,Q3,SUVsdfsdf

//	@GetMapping
//	Page<CarDTO> findAll(CarSearchRequest request) throws SQLException {
//		SearchRequest searchRequest = request.asSearchRequest();
//		return carService.findAllByFilter2(searchRequest).map(CarDTO::fromCar);
//	}

	@GetMapping
	ResponseEntity<List<CarDTO>> findAllWithPage(@RequestParam(required = false) Long make,
			@RequestParam(required = false) String make_name, @RequestParam(required = false) Long model,
			PageOptions pageOptions) throws SQLException {

		System.out.println(pageOptions);
		Long makeId = hasMakeName(make_name);
		List<Specification<Car>> predicates = Stream
				.of(Optional.ofNullable(makeId).map(CarSpecification::hasMakeId),
						Optional.ofNullable(model).map(CarSpecification::hasModelId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		return ResponseEntity.ok(CarList.fromCar(carService.findAllByFilter(predicates, pageOptions)));
	}

	private Long hasMakeName(String name) throws SQLException {
		Make make = makeService.findByName(name).get();
		return make.getId();
	}

	private Long hasModelName(String name) throws SQLException {
		Make make = makeService.findByName(name).get();
		return make.getId();
	}

	@PostMapping()
	ResponseEntity<CarDTO> save(@RequestBody Car car) throws SQLException {
		Car carToSave = carService.save(car);
		return ResponseEntity.ok(CarDTO.fromCar(carToSave));
	}
}
