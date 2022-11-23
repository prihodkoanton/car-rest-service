package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDTO {

	private Long id;
	private String name;
	private Make make;

	public static Model toModel(ModelDTO dto) {
		Model model = new Model();
		model.setId(dto.getId());
		model.setName(dto.getName());
		model.setMake(dto.getMake());
		return model;
	}

	public static ModelDTO fromModel(Model model) {
		ModelDTO dto = new ModelDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setMake(model.getMake());
		return dto;
	}
}
