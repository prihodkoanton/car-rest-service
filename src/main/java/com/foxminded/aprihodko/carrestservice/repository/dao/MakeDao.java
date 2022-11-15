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

import com.foxminded.aprihodko.carrestservice.model.Make;
import com.foxminded.aprihodko.carrestservice.model.PageOptions;
import com.foxminded.aprihodko.carrestservice.repository.dao.specification.Specification;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MakeDao {

	private final EntityManager em;

	@Transactional(readOnly = true)
	public List<Make> findAllByFilter(List<Specification<Make>> specifications, PageOptions pageOptions) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Make> query = criteriaBuilder.createQuery(Make.class);
		Root<Make> root = query.from(Make.class);

		Predicate[] predicates = specifications.stream().map(it -> it.toPredicate(root, query, criteriaBuilder))
				.collect(toList()).toArray(new Predicate[0]);
		return em.createQuery(query.select(root).where(predicates))
				.setFirstResult(pageOptions.getPage() * pageOptions.getPageSize()).getResultList();
	}

	@Transactional
	public List<Make> saveAll(List<Make> makeList) {
		makeList.forEach(em::persist);
		return makeList;
	}
}
