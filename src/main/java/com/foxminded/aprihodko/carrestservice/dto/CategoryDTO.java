package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Category;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

	private Long id;
	private String name;

	public static Category toCategory(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		return category;
	}

	public static CategoryDTO fromCategory(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		return dto;
	}
}
