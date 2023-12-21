package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.evalia.backend.rest.deserializers.AddressDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonDeserialize(using = AddressDeserializer.class)

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
public class Address {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@OneToOne(optional = false)
	private Country country;

	@NonNull
	@OneToOne(optional = false)
	private Governorate governorate;

	@NonNull
	@OneToOne(optional = false)
	private Delegation delegation;

	@Setter
	private String street;

	@Setter
	private Integer postalCode;

	@OneToOne(optional=false)
	private Actor actor;
}
