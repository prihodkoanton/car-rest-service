package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import com.foxminded.aprihodko.carrestservice.model.Make;

public class MakeSpecification {

	public static Specification<Make> hasName(String name) {
		return (root, cq, cb) -> cb.equal(root.get("name"), name);
	}
}
