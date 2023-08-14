package com.evalia.backend.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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
@Setter
@EqualsAndHashCode(callSuper = true)

@javax.persistence.Entity
@DiscriminatorValue("professional")
public class Professional extends Entity {

	@Column(nullable = false, unique = true)
	@NotBlank
	@NonNull
	private String taxIdentificationNumber;

}
