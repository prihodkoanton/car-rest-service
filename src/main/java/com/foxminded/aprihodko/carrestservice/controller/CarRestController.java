package com.foxminded.aprihodko.carrestservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/cars")
@RequiredArgsConstructor
public class CarRestController {

//    private final MakeService makeService;
//    private final ModelService modelService;
//    private final CategoryService categoryService;

//	`POST /api/v1/manufacturers/toyota/models/corolla/2001`
//	`GET /api/v1/cars?manufacturer=mercedes&minYear=2005`

//	objectId,Make,Year,Model,Category
//	ZRgPP9dBMm,Audi,2020,Q3,SUV

//    @GetMapping()
//    Page<ModelDTO> findAllModels(ModelSearchRequest request) {
//        SearchRequest searchRequest = request.asSerRequest();
//        Page<ModelDTO> result = modelService.findAllByFilter2(searchRequest).map(ModelDTO::fromModel);
//        return result;
//    }
//
//    @GetMapping("{year}")
//    ResponseEntity<List<ModelDTO>> findModelByName(@PathVariable(name = "year") int year) throws SQLException {
//        return ResponseEntity.ok(ModelList.fromModelList(modelService.findByYear(year)));
//    }

// NEED TO DO
//    @PostMapping
//    ResponseEntity<ModelDTO> newModel(ModelDTO dto) {
//        Model model = ModelDTO.toModel(dto);
//        Model toDTO = modelService.save(model);
//        return ResponseEntity.ok(ModelDTO.fromModel(toDTO));
//    }
// NEER OR NOT?
//	@GetMapping("{id}")
//	ResponseEntity<List<ModelDTO>> findModelsByMakeId(@PathVariable(name = "id") Long id) throws SQLException {
//		return ResponseEntity.ok(ModelList.fromModelList(modelService.findByMakeId(id)));
//	}

//    @DeleteMapping("{id}")
//    void deleteModelById(Long id) {
//        modelService.delete(id);
//    }
//
//    @DeleteMapping
//    void deleteModelByObject(@RequestBody ModelDTO dto) {
//        modelService.delete(ModelDTO.toModel(dto));
//    }
//
//    @GetMapping("/makes")
//    Page<MakeDTO> findAllMakes(MakeSearchRequest request) throws SQLException {
//        SearchRequest searchRequest = request.asSearchRequest();
//        Page<MakeDTO> result = makeService.findAllByFilter2(searchRequest).map(MakeDTO::fromMake);
//        return result;
//    }
//
//    @GetMapping("/makes/{name}")
//    ResponseEntity<MakeDTO> findMakeByName(@PathVariable(name = "name") String name) throws SQLException {
//        return ResponseEntity.ok(MakeDTO.fromMake(makeService.findByName(name).get()));
//    }
//
//    @PostMapping("/makes")
//    ResponseEntity<MakeDTO> newMake(@RequestBody MakeDTO dto) {
//        Make make = MakeDTO.toMake(dto);
//        Make toDTO = makeService.save(make);
//        return ResponseEntity.ok(MakeDTO.fromMake(toDTO));
//    }
//
//    @DeleteMapping("/makes/{id}")
//    void deleteMakeById(@PathVariable Long id) {
//        makeService.delete(id);
//    }
//
//    @DeleteMapping("/makes")
//    void deleteMakeByObject(@RequestBody MakeDTO dto) {
//        makeService.delete(MakeDTO.toMake(dto));
//    }
//
//    @GetMapping("/makes/categories")
//    Page<Category> findAllCategories(CategorySearchRequest request) {
//        SearchRequest searchRequest = request.asSearchRequest();
//        return categoryService.findAllByFilter2(searchRequest);
//    }
//
//    @GetMapping("/makes/categories/{name}")
//    ResponseEntity<CategoryDTO> findCategoryByName(@PathVariable(name = "name") String name) throws SQLException {
//        return ResponseEntity.ok(CategoryDTO.fromCategory(categoryService.findByUsername(name).get()));
//    }
//
//    @PostMapping("/makes/categories")
//    ResponseEntity<CategoryDTO> newCategory(@RequestBody CategoryDTO dto) {
//        Category category = CategoryDTO.toCategory(dto);
//        Category toDTO = categoryService.save(category);
//        return ResponseEntity.ok(CategoryDTO.fromCategory(toDTO));
//    }
//
//    @DeleteMapping("/makes/categories/{id}")
//    void deleteById(@PathVariable Long id) {
//        categoryService.delete(id);
//    }
//
//    @DeleteMapping("/makes/categories/")
//    void deleteByObject(@RequestBody CategoryDTO dto) {
//        categoryService.delete(CategoryDTO.toCategory(dto));
//    }
}
