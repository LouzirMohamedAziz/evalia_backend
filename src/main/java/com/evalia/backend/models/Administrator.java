package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name="administrator")
@Entity
public class Administrator{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long administratorId;
	

	public Administrator() {
	}

	public Administrator(Long administratorId) {
		this.administratorId = administratorId;
	}

	public Long getAdministratorId() {
		return this.administratorId;
	}

	public void setAdministratorId(Long administratorId) {
		this.administratorId = administratorId;
	}

	public Administrator administratorId(Long administratorId) {
		setAdministratorId(administratorId);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Administrator)) {
			return false;
		}
		Administrator administrator = (Administrator) o;
		return Objects.equals(administratorId, administrator.administratorId);
	}

	@Override
	public String toString() {
		return "{" +
			" administratorId='" + getAdministratorId() + "'" +
			"}";
	}
	
}
