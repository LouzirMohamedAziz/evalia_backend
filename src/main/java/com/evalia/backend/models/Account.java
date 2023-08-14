package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 90)
	@NotBlank
	@Size(max = 90)
	@NonNull
	@Setter
	private String login;
	
	@Column(nullable = false)
	@NotBlank
	@NonNull
	@Setter
	private String password;
	
	@Column(nullable = false)
	@Setter
	private boolean active = true;
	
	@Column(nullable = false)
	@Setter
	private Boolean verified;
	
	@OneToOne
	@Setter
	@EqualsAndHashCode.Exclude
	private Administrative administrative;
	
	@OneToOne
	@Setter
	@EqualsAndHashCode.Exclude
	private Entity entity;
	
	@OneToMany(fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	private List<Role> roles = new ArrayList<>();
}
