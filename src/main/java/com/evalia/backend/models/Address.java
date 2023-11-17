package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;

	private Integer houseNumber;

	private Integer postalCode;

	public Address() {
	}

	public Address(Long id, String street, Integer houseNumber, Integer postalCode) {
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHouseNumber() {
		return this.houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public Address id(Long id) {
		setId(id);
		return this;
	}

	public Address street(String street) {
		setStreet(street);
		return this;
	}

	public Address houseNumber(Integer houseNumber) {
		setHouseNumber(houseNumber);
		return this;
	}

	public Address postalCode(Integer postalCode) {
		setPostalCode(postalCode);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Address)) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, street, houseNumber, postalCode);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", street='" + getStreet() + "'" +
			", houseNumber='" + getHouseNumber() + "'" +
			", postalCode='" + getPostalCode() + "'" +
			"}";
	}

	
}
