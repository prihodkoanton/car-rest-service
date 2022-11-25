package com.foxminded.aprihodko.carrestservice.model.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SearchSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = -9153865343320759990L;

	private final transient SearchRequest request;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

		for (FilterRequest filter : this.request.getFilters()) {
			log.info("Filter: {} {} {}", filter.getKey(), filter.getOperator(), predicate);
		}

		List<Order> orders = new ArrayList<>();
		for (SortRequest sort : this.request.getSorts()) {
			orders.add(sort.getDirection().build(root, cb, sort));
		}

		query.orderBy(orders);
		return predicate;
	}

}
