package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelRequest {

	private int year;
	private Make make;

	public static Model toModel(ModelRequest request) {
		Model model = new Model(request.getYear(), request.getMake());
		return model;
	}

	public static ModelRequest fromModelRequest(Model model) {
		ModelRequest modelRequest = new ModelRequest();
		modelRequest.setYear(model.getYear());
		modelRequest.setMake(model.getMake());
		return modelRequest;
	}
}
