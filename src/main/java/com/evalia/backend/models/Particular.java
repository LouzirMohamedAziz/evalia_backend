package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@DiscriminatorValue("particular")
public class Particular extends Entity {

	@Column(unique = true, nullable = false)
	@NotBlank
	@Size(max = 50)
	@NonNull
	@Setter
	private String nationalIdentificationCode;
	
	@Column(nullable = false)
	@NotBlank
	@NonNull
	private String surname;

	@Column(nullable = false)
	@NotBlank
	@Temporal(TemporalType.DATE)
	@NonNull
	private Date birthDate;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private Address alternativeAddress;
}
