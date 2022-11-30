package com.foxminded.aprihodko.carrestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.foxminded.aprihodko.carrestservice.model.Category;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryRequest {

	private String name;

	public static Category toCategory(CategoryRequest request) {
		Category category = new Category(request.getName());
		return category;
	}

	public static CategoryRequest fromCategoryRequest(Category category) {
		CategoryRequest categoryRequest = new CategoryRequest();
		categoryRequest.setName(category.getName());
		return categoryRequest;
	}
}
