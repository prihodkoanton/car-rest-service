package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Car;

public class CarSpecification {

	public static Specification<Car> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Car> hasMakeId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("make"), id);
	}

	public static Specification<Car> hasModelId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get("model"), id);
	}
}
