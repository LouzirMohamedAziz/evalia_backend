package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String name;

	@NonNull
	@ManyToOne(optional = false)
	private Country country;

	@OneToMany(mappedBy = "governorate", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Delegation> delegations = new ArrayList<>();

	
	public void addDelegation(Delegation delegation) {
		delegation.setGovernorate(this);
		delegations.add(delegation);
	}

	public void setDelegations(List<Delegation> delegations) {
		if(Objects.isNull(delegations))
			return;
		this.delegations.clear();
		delegations.forEach(this::addDelegation);
	}
}
