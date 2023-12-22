package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.evalia.backend.rest.deserializers.AddressDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Mohamed Ben Hamouda
 *
 */
@JsonDeserialize(using = AddressDeserializer.class)
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@OneToOne(optional = false)
	private Actor actor;
	
	@NonNull
	@OneToOne(optional = false)
	private Country country;

	@NonNull
	@OneToOne(optional = false)
	private Governorate governorate;

	@OneToOne
	private Delegation delegation;

	private String street;

	private String postalCode;
}
