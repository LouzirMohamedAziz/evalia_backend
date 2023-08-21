package com.evalia.backend.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;

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
@DiscriminatorValue("professional")
@javax.persistence.Entity
public class Professional extends Entity {

	@Column(nullable = false, unique = true)
	@NonNull
	private String taxIdentificationNumber;

}
