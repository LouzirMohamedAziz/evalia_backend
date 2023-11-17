package com.evalia.backend.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table
public class Governorate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Delegation> delegations;

	public Governorate() {
	}

	public Governorate(Long id, String name, List<Delegation> delegations) {
		this.id = id;
		this.name = name;
		this.delegations = delegations;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Delegation> getDelegations() {
		return this.delegations;
	}

	public void setDelegations(List<Delegation> delegations) {
		this.delegations = delegations;
	}

	public Governorate id(Long id) {
		setId(id);
		return this;
	}

	public Governorate name(String name) {
		setName(name);
		return this;
	}

	public Governorate delegations(List<Delegation> delegations) {
		setDelegations(delegations);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Governorate)) {
			return false;
		}
		Governorate governorate = (Governorate) o;
		return Objects.equals(id, governorate.id) && Objects.equals(name, governorate.name) && Objects.equals(delegations, governorate.delegations);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, delegations);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", delegations='" + getDelegations() + "'" +
			"}";
	}

	
}
