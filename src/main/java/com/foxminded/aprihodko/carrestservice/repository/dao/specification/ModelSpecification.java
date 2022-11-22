package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import com.foxminded.aprihodko.carrestservice.model.Model;

public class ModelSpecification {

	public static Specification<Model> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Model> hasMakeId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("make"), id);
	}
}
