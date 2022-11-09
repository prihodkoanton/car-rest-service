package com.foxminded.aprihodko.carrestservice.model.security;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public BaseEntity(Long id, Date creted, Date updted, Status status) {
		this.id = id;
		this.creted = creted;
		this.updted = updted;
		this.status = status;
	}

	public BaseEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreted() {
		return creted;
	}

	public void setCreted(Date creted) {
		this.creted = creted;
	}

	public Date getUpdted() {
		return updted;
	}

	public void setUpdted(Date updted) {
		this.updted = updted;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(creted, other.creted) && Objects.equals(id, other.id) && status == other.status
				&& Objects.equals(updted, other.updted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(creted, id, status, updted);
	}
}
