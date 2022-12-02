package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;

public class CarSpecification {

//	public static Specification<Car> hasYear(Integer year) {
//		return (root, cq, cb) -> {
//			cq = cb.createQuery(Car.class);
//			cq.select(cb.max(root.get("year")));
//			root = cq.from(Car.class);
//			return cq.select(cb.max(root.get("year")));
//		};
//	}
	public static Specification<Car> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Car> hasMakeName(String makeName) {
		return (root, cq, cb) -> cb.like(root.<String>get("make").get("name"), "%" + makeName + "%");
	}

	public static Specification<Car> hasModelName(String modelName) {
		return (root, cq, cb) -> cb.like(root.<String>get("model").get("name"), "%" + modelName + "%");
	}

	public static Specification<Car> hasCategoryName(String categoryName) {
		return (root, cq, cb) -> {
			Join<Car, Category> carCategory = root.join("categories");
			return cb.like(carCategory.<String>get("name"), "%" + categoryName + "%");
		};
	}
}
