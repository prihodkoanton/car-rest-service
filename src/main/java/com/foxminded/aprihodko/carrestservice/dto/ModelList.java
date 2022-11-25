package com.foxminded.aprihodko.carrestservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Model;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelList {

	private List<ModelDTO> items;

	public static List<Model> toMakeList(List<ModelDTO> dtos) {
		List<Model> models = new ArrayList<>();
		dtos.forEach(dto -> {
			models.add(ModelDTO.toModel(dto));
		});
		return models;
	}

	public static List<ModelDTO> fromModelList(List<Model> models) {
		List<ModelDTO> dtos = new ArrayList<>();
		models.forEach(model -> {
			dtos.add(ModelDTO.fromModel(model));
		});
		return dtos;
	}

}
