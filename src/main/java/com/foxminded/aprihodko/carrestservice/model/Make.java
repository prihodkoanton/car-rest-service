package com.foxminded.aprihodko.carrestservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "makes")
@Data
@NoArgsConstructor
public class Make implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "make_name")
	private String name;

	public Make(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Make(String name) {
		this.name = name;
	}

}
