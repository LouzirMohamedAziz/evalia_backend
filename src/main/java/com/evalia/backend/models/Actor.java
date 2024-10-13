package com.evalia.backend.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import com.evalia.backend.utils.validator.annotation.AddressConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hamdi Jandoubi
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@Type(value = Civil.class, name = "Personal"),
		@Type(value = LegalEntity.class, name = "LegalEntity"),
		@Type(value = Professional.class, name = "Professional") })

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actor_type")
public abstract class Actor {

	@Id
	@EqualsAndHashCode.Include
	private String identifier;

	@AddressConstraint
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Address address;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Image avatar;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Image cover;
	
	@Nullable
	private String description;

}
