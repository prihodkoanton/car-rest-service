package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;
import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.Model;

public class CarSpecification {

	public static Specification<Car> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Car> hasMakeName(String makeName) {
		return (root, cq, cb) -> {
			Join<Make, Car> carMake = root.join("make");
			return cb.equal(cb.lower(carMake.<String>get("name")), makeName);
		};
	}

	public static Specification<Car> hasModelName(String modelName) {
		return (root, cq, cb) -> {
			Join<Model, Car> carModel = root.join("model");
			return cb.equal(cb.lower(carModel.<String>get("name")), modelName);
		};
	}

	public static Specification<Car> hasCategoryName(String categoryName) {
		return (root, cq, cb) -> {
			Join<Category, Car> carCategory = root.join("categories");
			return cb.equal(carCategory.get("name"), categoryName);
		};
	}
}
