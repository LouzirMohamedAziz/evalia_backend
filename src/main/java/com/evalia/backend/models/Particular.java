package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@DiscriminatorValue("particular")
@javax.persistence.Entity
public class Particular extends Entity {

	@Column(unique = true, nullable = false)
	@NonNull
	@Setter
	private String nationalIdentificationCode;
	
	@Column(nullable = false)
	@NonNull
	private String surname;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@NonNull
	private Date birthDate;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private Address alternativeAddress;
}
