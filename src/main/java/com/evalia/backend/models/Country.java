package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Country {

	
	@Id
	private String isoCode;

	@NotBlank
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy="country",cascade=CascadeType.ALL,orphanRemoval = true)
	private List<Governorate> governorates = new ArrayList<>();
	
	public void addGovernorate(Governorate governorate) {
		governorates.add(governorate);
		governorate.setCountry(this);
	}
	
	public void removeGovernorate(Governorate governorate) {
		governorates.remove(governorate);
		governorate.setCountry(null);
	}
}
