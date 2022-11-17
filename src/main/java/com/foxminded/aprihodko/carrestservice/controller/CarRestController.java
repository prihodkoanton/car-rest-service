package com.foxminded.aprihodko.carrestservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//	objectId,Make,Year,Model,Category
//	`POST /api/v1/manufacturers/toyota/models/corolla/2001`
//	`GET /api/v1/cars?manufacturer=mercedes&minYear=2005`

}
