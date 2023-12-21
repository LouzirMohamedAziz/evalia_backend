package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;

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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
public class Governorate {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	@NotBlank
	@Size(max = 90)
	@NonNull
	private String name;

	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	private Country country;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "governorate")
	private List<Delegation> delegations = new ArrayList<>();
	
	
	public void addDelegation(Delegation delegation) {
		delegations.add(delegation);
		delegation.setGovernorate(this);
	}
	
	public void removeDelegation(Delegation delegation) {
		delegations.remove(delegation);
		delegation.setGovernorate(null);
	}
}
