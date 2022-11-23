package com.foxminded.aprihodko.carrestservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Car;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarList {

	List<CarDTO> items;

	public static List<Car> toCarList(List<CarDTO> dtos) {
		List<Car> cars = new ArrayList<>();
		dtos.forEach(dto -> {
			cars.add(CarDTO.toCar(dto));
		});
		return cars;
	}

	public static List<CarDTO> fromCarDTO(List<Car> cars) {
		List<CarDTO> dtos = new ArrayList<>();
		cars.forEach(car -> {
			dtos.add(CarDTO.fromCar(car));
		});
		return dtos;
	}
}
