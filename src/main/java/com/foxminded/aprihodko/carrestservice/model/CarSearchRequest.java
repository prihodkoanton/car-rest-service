package com.foxminded.aprihodko.carrestservice.model;

import static com.foxminded.aprihodko.carrestservice.model.search.SearchRequest.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.foxminded.aprihodko.carrestservice.model.search.FieldType;
import com.foxminded.aprihodko.carrestservice.model.search.FilterRequest;
import com.foxminded.aprihodko.carrestservice.model.search.Operator;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SortDirection;
import com.foxminded.aprihodko.carrestservice.model.search.SortRequest;

import lombok.Data;

@Data
public class CarSearchRequest {
	private Integer page = 0;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private Integer year;
	private Long make_id;
	private Long model_id;
	private Set<Category> categories;
	private List<String> sortBy = new ArrayList<>();

	public SearchRequest asSearchRequest() {
		List<FilterRequest> filters = new ArrayList<>();
		List<SortRequest> sortRequests = getSortBy().stream()
				.map(column -> SortRequest.builder().key(column).direction(SortDirection.ASC).build()).collect(toList());
		if (getYear() != null && getMake_id() != null && getModel_id() != null && getCategories() != null) {
			filters.add(FilterRequest.builder().fieldType(FieldType.INTEGER).key("year").Operator(Operator.LIKE)
					.value(getYear()).build());
			filters.add(FilterRequest.builder().fieldType(FieldType.LONG).key("make_id").Operator(Operator.LIKE)
					.value(getMake_id()).build());
			filters.add(FilterRequest.builder().fieldType(FieldType.LONG).key("model_id").Operator(Operator.LIKE)
					.value(getMake_id()).build());
			filters.add(FilterRequest.builder().fieldType(FieldType.LONG).key("category_id").Operator(Operator.LIKE)
					.value(getMake_id()).build());

		}
		return new SearchRequest(filters, sortRequests, getPage(), getPageSize());
	}
}
