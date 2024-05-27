package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohamed Ben Hamouda
 *
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotNull(message = "Actor in Address must not be null")
//	@OneToOne(optional = false)
//	private Actor actor;
	
	@NotNull
	@OneToOne(optional = false)
	private Country country;

	@NotNull
	@OneToOne(optional = false)
	private Governorate governorate;

	@OneToOne
	private Delegation delegation;

	private String street;

	private String postalCode;
}
