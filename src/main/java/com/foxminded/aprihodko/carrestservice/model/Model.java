package com.foxminded.aprihodko.carrestservice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "models")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "categories")
@ToString(exclude = "categories")
public class Model implements Serializable {

	private static final long serialVersionUID = 93368885188778801L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "year")
	private int year;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "make_ref", referencedColumnName = "ID")
	private Make make;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "models_categories", joinColumns = { @JoinColumn(name = "model_id") }, inverseJoinColumns = {
			@JoinColumn(name = "category_id") })
	private Set<Category> categories = new HashSet<>();

	public Model(Long id, int year, Make make) {
		this.id = id;
		this.year = year;
		this.make = make;
	}

	public Model(int year, Make make) {
		this.year = year;
		this.make = make;
	}
}
