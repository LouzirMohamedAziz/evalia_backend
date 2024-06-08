package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.evalia.backend.rest.deserializers.CountryDeserializer;
import com.evalia.backend.rest.deserializers.DelegationDeserializer;
import com.evalia.backend.rest.deserializers.GovernorateDeserializer;
import com.evalia.backend.rest.serializers.CountrySerializer;
import com.evalia.backend.rest.serializers.DelegationSerializer;
import com.evalia.backend.rest.serializers.GovernorateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	
	@JsonSerialize(using = CountrySerializer.class)
	@JsonDeserialize(using = CountryDeserializer.class)
	@NotNull
	@ManyToOne(optional = false)
	private Country country;

	@JsonDeserialize(using = GovernorateDeserializer.class)
	@JsonSerialize(using = GovernorateSerializer.class)
	@NotNull
	@ManyToOne(optional = false)
	private Governorate governorate;

	@JsonDeserialize(using = DelegationDeserializer.class)
	@JsonSerialize(using = DelegationSerializer.class)
	@ManyToOne
	private Delegation delegation;

	private String street;

	private String postalCode;
}
