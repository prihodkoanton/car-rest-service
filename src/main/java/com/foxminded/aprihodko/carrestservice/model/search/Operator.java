package com.foxminded.aprihodko.carrestservice.model.search;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Operator {

	EQUAL {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Object value = request.getFieldType().parse(request.getValue().toString());
			Expression<?> key = root.get(request.getKey());
			return cb.and(cb.equal(key, value), predicate);
		}
	},
	NOT_EQUAL {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Object value = request.getFieldType().parse(request.getValue().toString());
			Expression<?> key = root.get(request.getKey());
			return cb.and(cb.notEqual(key, value), predicate);
		}
	},

	LIKE {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Expression<String> key = root.get(request.getKey());
			return cb.and(cb.like(cb.upper(key), "%" + request.getValue().toString().toUpperCase() + "%"), predicate);
		}
	},

	IN {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			List<Object> values = request.getValues();
			CriteriaBuilder.In<Object> inClause = cb.in(root.get(request.getKey()));
			for (Object value : values) {
				inClause.value(request.getFieldType().parse(value.toString()));
			}
			return cb.and(inClause, predicate);
		}
	},

	BETWEEN {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Object value = request.getFieldType().parse(request.getValue().toString());
			Object valueTo = request.getFieldType().parse(request.getValueTo().toString());
			if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
				Number start = (Number) value;
				Number end = (Number) valueTo;
				Expression<Number> key = root.get(request.getKey());
				return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
			}
			log.info("Can not use between for {} field type.", request.getFieldType());
			return predicate;
		}
	},

	GREATER {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Object value = request.getFieldType().parse(request.getValue().toString());
			if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
				Number start = (Number) value;
				Expression<Number> key = root.get(request.getKey());
				return cb.and(cb.and(cb.ge(key, start)), predicate);
			}
			log.info("Can not use greater for {} field type.", request.getFieldType());
			return predicate;
		}
	},

	LESS {
		@Override
		public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate) {
			Object value = request.getFieldType().parse(request.getKey().toString());
			if (request.getFieldType() != FieldType.CHAR && request.getFieldType() != FieldType.BOOLEAN) {
				Number start = (Number) value;
				Expression<Number> key = root.get(request.getKey());
				return cb.and(cb.and(cb.le(key, start)), predicate);
			}
			log.info("Can not use less for {} field type.", request.getFieldType());
			return predicate;
		}
	};

	public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequest request, Predicate predicate);
}
