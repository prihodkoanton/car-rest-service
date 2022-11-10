package com.foxminded.aprihodko.carrestservice.model;

import static com.foxminded.aprihodko.carrestservice.model.search.SearchRequest.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.aprihodko.carrestservice.model.search.FieldType;
import com.foxminded.aprihodko.carrestservice.model.search.FilterRequest;
import com.foxminded.aprihodko.carrestservice.model.search.Operator;
import com.foxminded.aprihodko.carrestservice.model.search.SearchRequest;
import com.foxminded.aprihodko.carrestservice.model.search.SortDirection;
import com.foxminded.aprihodko.carrestservice.model.search.SortRequest;

import lombok.Data;

@Data
public class MakeSearchRequest {
	private Integer page = 0;
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private String name;
	private List<String> sortBy = new ArrayList<>();

	public SearchRequest asSearchRequest() {
		List<FilterRequest> filters = new ArrayList<>();
		List<SortRequest> sortRequest = getSortBy().stream()
				.map(column -> SortRequest.builder().key(column).direction(SortDirection.ASC).build()).collect(toList());
		if (getName() != null) {
			filters.add(FilterRequest.builder().fieldType(FieldType.STRING).key("name").Operator(Operator.LIKE)
					.value(getName()).build());
		}
		return new SearchRequest(filters, sortRequest, getPage(), getPageSize());
	}
}
