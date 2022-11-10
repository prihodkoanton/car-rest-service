package com.foxminded.aprihodko.carrestservice.repository.dao;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.foxminded.aprihodko.carrestservice.model.Model;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelDao {

	private final EntityManager em;

	@Transactional(readOnly = true)
	public List<Model> findAllByFilter(List<Specification<Model>> specifications, PageOptions pageOptions) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Model> query = criteriaBuilder.createQuery(Model.class);
		Root<Model> root = query.from(Model.class);

		Predicate[] predicates = specifications.stream().map(it -> it.toPredicate(root, query, criteriaBuilder))
				.collect(toList()).toArray(new Predicate[0]);
		return em.createQuery(query.select(root).where(predicates))
				.setFirstResult(pageOptions.getPage() * pageOptions.getPageSize()).setMaxResults(pageOptions.getPageSize())
				.getResultList();
	}

	public List<Model> saveAll(List<Model> models) {
		models.forEach(em::persist);
		return models;
	}
}
