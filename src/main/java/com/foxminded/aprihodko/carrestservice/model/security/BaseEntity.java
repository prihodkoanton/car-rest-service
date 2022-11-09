package com.foxminded.aprihodko.carrestservice.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@CreatedDate
	@Column(name = "created")
	private Date creted;

	@LastModifiedDate
	@Column(name = "updated")
	private Date updted;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
}
