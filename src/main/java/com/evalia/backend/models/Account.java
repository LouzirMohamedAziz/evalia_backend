package com.evalia.backend.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@javax.persistence.Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountid;
	
	@Column(unique = true, nullable = false, length = 90)
	@NonNull
	private String login;
	
	@Column(nullable = false)
	@NonNull
	private String password;
	
	@Column(nullable = false)
	private boolean isActive = true;
	
	@Column(nullable = false)
	private Boolean verified;
	
	@OneToMany
	private Entity entity;
	
}
