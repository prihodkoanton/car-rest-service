package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Make;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MakeRequest {

	private String name;

	public static Make toMake(MakeRequest request) {
		Make make = new Make(request.getName());
		return make;
	}

	public static MakeRequest fromMakeRequest(Make make) {
		MakeRequest makeRequest = new MakeRequest();
		makeRequest.setName(make.getName());
		return makeRequest;
	}
}
