package com.evalia.backend.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
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
@EqualsAndHashCode

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type")
public abstract class Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@NonNull
	@Setter
	private String name;
	
	@Column(unique = true, nullable = false, length = 90)
	@NotBlank
	@Size(max = 90)
	@Email
	@NonNull
	@Setter
	private String email;
	
	@NotBlank
	@NonNull
	@Setter
	private String phone;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, optional = false, orphanRemoval = true)
	@NotBlank
	@NonNull
	@Setter
	@EqualsAndHashCode.Exclude
	private Address address;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@EqualsAndHashCode.Exclude
	private Account account;
	
}
