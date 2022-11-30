package com.foxminded.aprihodko.carrestservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MakeList {

	private List<MakeDTO> items;

	public static List<Make> toMakeList(List<MakeDTO> dtos) {
		List<Make> makes = new ArrayList<>();
		dtos.forEach(dto -> {
			makes.add(MakeDTO.toMake(dto));
		});
		return makes;
	}

	public static List<MakeDTO> fromMakeList(List<Make> makes) {
		List<MakeDTO> dtos = new ArrayList<>();
		makes.forEach(make -> {
			dtos.add(MakeDTO.fromMake(make));
		});
		return dtos;
	}
}
