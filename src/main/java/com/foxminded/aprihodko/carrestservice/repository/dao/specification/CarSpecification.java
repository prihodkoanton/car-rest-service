package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import java.util.Set;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;

public class CarSpecification {

	public static Specification<Car> hasName(String name) {
		return (root, cq, cb) -> cb.equal(root.get("name"), name);
	}

	public static Specification<Car> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Car> hasMakeId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("make"), id);
	}

	public static Specification<Car> hasModelId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("model"), id);
	}

	public static Specification<Car> hasCategory(Set<Category> categories) {
		return (root, cq, cb) -> cb.equal(root.get("categories"), categories);
	}
}
