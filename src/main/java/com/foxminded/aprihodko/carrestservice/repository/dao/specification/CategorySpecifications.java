package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import com.foxminded.aprihodko.carrestservice.model.Category;

public class CategorySpecifications {

	public static Specification<Category> hasName(String name) {
		return (root, cq, cb) -> cb.equal(root.get("name"), name);
	}
}
