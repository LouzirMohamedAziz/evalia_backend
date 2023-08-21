package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Country {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	
	@Column(unique = true, nullable = false)
	@NonNull
	@Setter
	private String isoCode;
	
	@Column(unique = true, nullable = false)
	@NonNull
	@Setter
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private List<Governorate> governorates = new ArrayList<>();
}
