package com.foxminded.aprihodko.carrestservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "makes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Make implements Serializable {

	private static final long serialVersionUID = 5232179329688730940L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "make_name")
	private String name;

	public Make(String name) {
		this.name = name;
	}
}
