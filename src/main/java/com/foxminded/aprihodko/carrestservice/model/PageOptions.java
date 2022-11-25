package com.foxminded.aprihodko.carrestservice.model;

import lombok.Data;

@Data
public class PageOptions {
	private Integer page = 0;
	private Integer pageSize = 10;
}
