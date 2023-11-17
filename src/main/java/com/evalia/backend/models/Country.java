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


@Table
@Entity
public class Country {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	
	private String isoCode;
	
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Governorate> governorates;

	public Country() {
	}

	public Country(Long countryId, String isoCode, String name, List<Governorate> governorates) {
		this.countryId = countryId;
		this.isoCode = isoCode;
		this.name = name;
		this.governorates = governorates;
	}

	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Governorate> getGovernorates() {
		return this.governorates;
	}

	public void setGovernorates(List<Governorate> governorates) {
		this.governorates = governorates;
	}

	public Country countryId(Long countryId) {
		setCountryId(countryId);
		return this;
	}

	public Country isoCode(String isoCode) {
		setIsoCode(isoCode);
		return this;
	}

	public Country name(String name) {
		setName(name);
		return this;
	}

	public Country governorates(List<Governorate> governorates) {
		setGovernorates(governorates);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Country)) {
			return false;
		}
		Country country = (Country) o;
		return Objects.equals(countryId, country.countryId) && Objects.equals(isoCode, country.isoCode) && Objects.equals(name, country.name) && Objects.equals(governorates, country.governorates);
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryId, isoCode, name, governorates);
	}

	@Override
	public String toString() {
		return "{" +
			" countryId='" + getCountryId() + "'" +
			", isoCode='" + getIsoCode() + "'" +
			", name='" + getName() + "'" +
			", governorates='" + getGovernorates() + "'" +
			"}";
	}

	
}
