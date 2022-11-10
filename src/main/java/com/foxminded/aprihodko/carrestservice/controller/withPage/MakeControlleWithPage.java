package com.foxminded.aprihodko.carrestservice.controller.withPage;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.MakeSearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.service.MakeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v2/makes")
@RequiredArgsConstructor
public class MakeControlleWithPage {

	private final MakeService makeService;

	Page<Make> findAll(MakeSearchRequest request) {
		SearchRequest searchRequest = request.asSearchRequest();
		return makeService.findAllFiltered2(searchRequest);
	}
}
