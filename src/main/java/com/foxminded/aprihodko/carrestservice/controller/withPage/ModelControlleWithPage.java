package com.foxminded.aprihodko.carrestservice.controller.withPage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v2/models")
@RequiredArgsConstructor
public class ModelControlleWithPage {

//    private final ModelService modelService;
//
//    @GetMapping
//    Page<Model> findAll(ModelSearchRequest request) {
//        SearchRequest searchRequest = request.asSerRequest();
//        return modelService.findAllByFilter2(searchRequest);
//    }
}
