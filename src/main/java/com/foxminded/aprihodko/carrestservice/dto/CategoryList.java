package com.foxminded.aprihodko.carrestservice.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Category;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryList {

	private List<CategoryDTO> items;

	public static List<Category> toCategoryList(List<CategoryDTO> dtos) {
		List<Category> catoCategories = new ArrayList<>();
		dtos.forEach(dto -> {
			catoCategories.add(CategoryDTO.toCategory(dto));
		});
		return catoCategories;
	}

	public static List<CategoryDTO> fromCategoryList(List<Category> categories) {
		List<CategoryDTO> dtos = new ArrayList<>();
		categories.forEach(category -> {
			dtos.add(CategoryDTO.fromCategory(category));
		});
		return dtos;
	}
}
