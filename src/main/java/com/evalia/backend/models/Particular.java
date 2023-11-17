package com.evalia.backend.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="particular")
// @EqualsAndHashCode(callSuper = true)
public class Particular{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long particularId;

	@Column(nullable = false)
	private String nationalIdentificationCode;
	
	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;


	public Particular() {
	}

	public Particular(Long particularId, String nationalIdentificationCode, String fullName, Date birthDate) {
		this.particularId = particularId;
		this.nationalIdentificationCode = nationalIdentificationCode;
		this.fullName = fullName;
		this.birthDate = birthDate;
	}

	public Long getParticularId() {
		return this.particularId;
	}

	public void setParticularId(Long particularId) {
		this.particularId = particularId;
	}

	public String getNationalIdentificationCode() {
		return this.nationalIdentificationCode;
	}

	public void setNationalIdentificationCode(String nationalIdentificationCode) {
		this.nationalIdentificationCode = nationalIdentificationCode;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Particular particularId(Long particularId) {
		setParticularId(particularId);
		return this;
	}

	public Particular nationalIdentificationCode(String nationalIdentificationCode) {
		setNationalIdentificationCode(nationalIdentificationCode);
		return this;
	}

	public Particular fullName(String fullName) {
		setFullName(fullName);
		return this;
	}

	public Particular birthDate(Date birthDate) {
		setBirthDate(birthDate);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Particular)) {
			return false;
		}
		Particular particular = (Particular) o;
		return Objects.equals(particularId, particular.particularId) && Objects.equals(nationalIdentificationCode, particular.nationalIdentificationCode) && Objects.equals(fullName, particular.fullName) && Objects.equals(birthDate, particular.birthDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(particularId, nationalIdentificationCode, fullName, birthDate);
	}

	@Override
	public String toString() {
		return "{" +
			" particularId='" + getParticularId() + "'" +
			", nationalIdentificationCode='" + getNationalIdentificationCode() + "'" +
			", fullName='" + getFullName() + "'" +
			", birthDate='" + getBirthDate() + "'" +
			"}";
	}
	

}