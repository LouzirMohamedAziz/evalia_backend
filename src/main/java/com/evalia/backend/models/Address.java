package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional = false)
	@NotBlank
	@NonNull
	@Setter
	private Country country;

	@OneToOne(optional = false)
	@NotBlank
	@NonNull
	@Setter
	private Governorate governorate;

	@OneToOne(optional = false)
	@NotBlank
	@NonNull
	@Setter
	private Delegation delegation;

	@Setter
	private String street;

	@Setter
	private Integer houseNumber;

	@Setter
	private Integer postalCode;

}
