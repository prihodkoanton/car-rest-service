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
public class CarRequest {

	private int year;
	private Make make;
	private Model model;
	private Set<Category> categories;

	public static Car toCar(CarRequest request) {
		Car car = new Car();
		car.setMake(request.getMake());
		car.setModel(request.getModel());
		car.setCategories(request.getCategories());
		return car;
	}

	public static CarRequest fromCarRequest(Car car) {
		CarRequest carRequest = new CarRequest();
		carRequest.setMake(car.getMake());
		carRequest.setModel(car.getModel());
		carRequest.setCategories(car.getCategories());
		return carRequest;
	}

}
