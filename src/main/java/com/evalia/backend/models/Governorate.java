package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Getter
@Setter

@Entity
public class Governorate {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(name = "governorate_name")
	private String name;
	
	@NotNull
	@ManyToOne(optional = false)
	@JsonIgnore
	private Country country;

	@JsonIgnore
	@OneToMany(mappedBy = "governorate", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Delegation> delegations = new ArrayList<>();


	public void addDelegation(Delegation delegation) {
		
		Objects.requireNonNull(delegation)
			.setGovernorate(this);
		
		delegations.add(delegation);
	}

	
	public void setDelegations(List<Delegation> delegations) {
		
		Objects.requireNonNull(delegations);
		
		this.delegations.clear();
		delegations.forEach(this::addDelegation);
	}
	
	
	public String toString() {
		return String.valueOf(id);
	}
}
