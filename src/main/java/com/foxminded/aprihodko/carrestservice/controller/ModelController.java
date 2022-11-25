package com.foxminded.aprihodko.carrestservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

//    private final ModelService modelService;
//
//    @GetMapping
//    ResponseEntity<List<ModelDTO>> findAll(@RequestParam(required = false) Integer year,
//            @RequestParam(required = false) Long id, PageOptions pageOptions) {
//        System.out.println(pageOptions);
//        List<Specification<Model>> predicates = Stream.of(Optional.ofNullable(year).map(ModelSpecification::hasYear))
//                .filter(Optional::isPresent).map(Optional::get).collect(toList());
//        predicates = Stream.of(Optional.ofNullable(id).map(ModelSpecification::hasMakeId)).filter(Optional::isPresent)
//                .map(Optional::get).collect(toList());
//        return ResponseEntity.ok(ModelList.fromModelList(modelService.findAllByFilter(predicates, pageOptions)));
//    }
//
//    @GetMapping("year/{year}")
//    ResponseEntity<List<ModelDTO>> findByYear(@PathVariable(name = "year") int year) throws SQLException {
//        return ResponseEntity.ok(ModelList.fromModelList(modelService.findByYear(year)));
//    }
//
//    @GetMapping("{id}")
//    ResponseEntity<ModelDTO> findById(@PathVariable(name = "id") Long id) throws SQLException {
//        return ResponseEntity.ok(ModelDTO.fromModel(modelService.findById(id).get()));
//    }
//
//    @PostMapping
//    ResponseEntity<ModelDTO> newModel(ModelDTO dto) {
//        Model model = ModelDTO.toModel(dto);
//        Model toDTO = modelService.save(model);
//        return ResponseEntity.ok(ModelDTO.fromModel(toDTO));
//    }
//
//    @GetMapping("byMakeId/{id}")
//    ResponseEntity<List<ModelDTO>> findByMakeId(@PathVariable(name = "id") Long id) throws SQLException {
//        return ResponseEntity.ok(ModelList.fromModelList(modelService.findByMakeId(id)));
//    }
//
//    @DeleteMapping("{id}")
//    void deleteById(Long id) {
//        modelService.delete(id);
//    }
//
//    @DeleteMapping
//    void deleteByObject(@RequestBody ModelDTO dto) {
//        modelService.delete(ModelDTO.toModel(dto));
//    }
}
