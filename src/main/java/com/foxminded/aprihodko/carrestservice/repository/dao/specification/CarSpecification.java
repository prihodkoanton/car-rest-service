package com.foxminded.aprihodko.carrestservice.repository.dao.specification;

import java.util.Collection;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.Category;

public class CarSpecification {

	public static Specification<Car> hasYear(Integer year) {
		return (root, cq, cb) -> cb.equal(root.get("year"), year);
	}

	public static Specification<Car> hasMakeName(String makeName) {
		return (root, cq, cb) -> cb.equal(root.get("make").get("name"), makeName);
	}

	public static Specification<Car> hasModelName(String modelName) {
		return (root, cq, cb) -> cb.equal(root.get("model").get("name"), modelName);
	}

	public static Specification<Car> hasCategoryName(String categoryName) {
		return (root, cq, cb) -> {
			cq.distinct(true);
			Root<Car> car = root;
			Root<Category> category = cq.from(Category.class);
			Expression<Collection<Car>> carCategory = car.get("categories");
			return cb.and(cb.equal(category.get("name"), categoryName), cb.isMember(car, carCategory));
		};
	}
}
