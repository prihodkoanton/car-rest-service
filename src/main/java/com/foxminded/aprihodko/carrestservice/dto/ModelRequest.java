package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelRequest {

	private String name;
	private Make make;

	public static Model toModel(ModelRequest request) {
		Model model = new Model();
		model.setName(request.getName());
		model.setMake(request.getMake());
		return model;
	}

	public static ModelRequest fromModelRequest(Model model) {
		ModelRequest modelRequest = new ModelRequest();
		modelRequest.setName(model.getName());
		modelRequest.setMake(model.getMake());
		return modelRequest;
	}
}
