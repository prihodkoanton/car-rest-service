package com.foxminded.aprihodko.carrestservice.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.dto.CarDTO;
import com.foxminded.aprihodko.carrestservice.dto.CarList;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.CarSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
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

	@GetMapping
	Page<CarDTO> findAll(CarSearchRequest request) throws SQLException {
		SearchRequest searchRequest = request.asSearchRequest();
		return carService.findAllByFilter2(searchRequest).map(CarDTO::fromCar);
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

	@GetMapping("/make{name}")
	ResponseEntity<CarDTO> findByName(@PathVariable(name = "name") String name) throws SQLException {
		Car car = carService.findByName(name).get();
		return ResponseEntity.ok(CarDTO.fromCar(car));
	}
}
