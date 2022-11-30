package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Category;

public class CategorySpecification {

	public static Specification<Category> hasName(String name) {
		return (root, cq, cb) -> cb.equal(root.get("name"), name);
	}

}
