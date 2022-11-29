package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MakeDTO {

	private Long id;
	private String name;

	public static Make toMake(MakeDTO dto) {
		Make make = new Make();
		make.setId(dto.getId());
		make.setName(dto.getName());
		return make;
	}

	public static MakeDTO fromMake(Make make) {
		MakeDTO dto = new MakeDTO();
		dto.setId(make.getId());
		dto.setName(make.getName());
		return dto;
	}
}
