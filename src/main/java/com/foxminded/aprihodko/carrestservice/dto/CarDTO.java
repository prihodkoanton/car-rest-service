package com.foxminded.aprihodko.carrestservice.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDTO {

	private Long id;
	private String name;
	private int year;
	private Make make;
	private Model model;
	Set<Category> categories;

	public static Car toCar(CarDTO dto) {
		Car car = new Car();
		car.setId(dto.getId());
		car.setName(dto.getName());
		car.setYear(dto.getYear());
		car.setMake(dto.getMake());
		car.setModel(dto.getModel());
		car.setCategories(dto.getCategories());
		return car;
	}

	public static CarDTO fromCar(Car car) {
		CarDTO dto = new CarDTO();
		dto.setId(car.getId());
		dto.setName(car.getName());
		dto.setYear(car.getYear());
		dto.setMake(car.getMake());
		dto.setModel(car.getModel());
		dto.setCategories(car.getCategories());
		return dto;
	}
}
