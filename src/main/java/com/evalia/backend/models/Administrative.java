package com.evalia.backend.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
public class Administrative{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	@NonNull
	
	private String name;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	@NonNull
	@Setter
	private String surname;
	
	@Column(nullable = false)
	@NotBlank
	@Email
	@NonNull
	@Setter
	private String email;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@Setter
	@EqualsAndHashCode.Exclude
	private Account account;
}
