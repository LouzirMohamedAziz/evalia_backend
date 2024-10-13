package com.evalia.backend.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter

@Entity
@DiscriminatorValue("legal_entity")
public class LegalEntity extends Ratable {
	
	@NotBlank
	private String name;
}
