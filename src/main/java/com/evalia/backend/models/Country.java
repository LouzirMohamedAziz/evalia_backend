package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohamed Ben Hamouda
 *
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Setter
@Getter

@Entity
public class Country {

	@EqualsAndHashCode.Include
	@Id
	private String isoCode;

	@NotBlank
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Governorate> governorates = new ArrayList<>();

	
	public void addGovernorate(Governorate governorate) {
		governorate.setCountry(this);
		governorates.add(governorate);
	}

	public void setGovernorates(List<Governorate> governorates) {
		if(Objects.isNull(governorates))
			return;
		this.governorates.clear();
		governorates.forEach(this::addGovernorate);
	}
	
	public String toString() {
		return this.isoCode;
	}
}
