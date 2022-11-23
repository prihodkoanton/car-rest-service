package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import com.foxminded.aprihodko.carrestservice.model.Model;

public class ModelSpecification {

	public static Specification<Model> hasName(String name) {
		return (root, cq, cb) -> cb.equal(root.get("name"), name);
	}

	public static Specification<Model> hasMakeId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("make"), id);
	}
}
