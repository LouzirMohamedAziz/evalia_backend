package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="professional")
// @DiscriminatorValue("Professional")
public class Professional{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long professionalId;

	private String taxIdentificationNumber;


	public Professional() {
	}

	public Professional(Long professionalId, String taxIdentificationNumber) {
		this.professionalId = professionalId;
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public Long getProfessionalId() {
		return this.professionalId;
	}

	public void setProfessionalId(Long professionalId) {
		this.professionalId = professionalId;
	}

	public String getTaxIdentificationNumber() {
		return this.taxIdentificationNumber;
	}

	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		this.taxIdentificationNumber = taxIdentificationNumber;
	}

	public Professional professionalId(Long professionalId) {
		setProfessionalId(professionalId);
		return this;
	}

	public Professional taxIdentificationNumber(String taxIdentificationNumber) {
		setTaxIdentificationNumber(taxIdentificationNumber);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Professional)) {
			return false;
		}
		Professional professional = (Professional) o;
		return Objects.equals(professionalId, professional.professionalId) && Objects.equals(taxIdentificationNumber, professional.taxIdentificationNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(professionalId, taxIdentificationNumber);
	}

	@Override
	public String toString() {
		return "{" +
			" professionalId='" + getProfessionalId() + "'" +
			", taxIdentificationNumber='" + getTaxIdentificationNumber() + "'" +
			"}";
	}
	
}
