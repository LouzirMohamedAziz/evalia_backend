package com.evalia.backend.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

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

@JsonTypeInfo(
	use = JsonTypeInfo.Id.NAME, 
	include = JsonTypeInfo.As.PROPERTY, 
	property = "type")
@JsonSubTypes({ 
		  @Type(value = Civil.class, name = "Personal"), 
		  @Type(value = Professional.class, name = "Professional")})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actor_type")
public abstract class Actor {

	@EqualsAndHashCode.Include
	@Id
	private String identifier;

	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private Address address;
	
}
