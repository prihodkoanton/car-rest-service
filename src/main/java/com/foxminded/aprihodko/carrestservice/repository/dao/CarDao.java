package com.foxminded.aprihodko.carrestservice.repository.dao;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Car;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarDao {

	private final EntityManager em;

	public List<Car> findAllByFilter(List<Specification<Car>> specifications, PageOptions pageOptions) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
		Root<Car> root = query.from(Car.class);

		Predicate[] predicates = specifications.stream().map(it -> it.toPredicate(root, query, criteriaBuilder))
				.collect(toList()).toArray(new Predicate[0]);

		return em.createQuery(query.select(root).where(predicates))
				.setFirstResult(pageOptions.getPage() * pageOptions.getPageSize()).setMaxResults(pageOptions.getPageSize())
				.getResultList();
	}

	@Transactional
	public List<Car> saveAll(List<Car> cars) {
		cars.forEach(em::persist);
		return cars;
	}
}
