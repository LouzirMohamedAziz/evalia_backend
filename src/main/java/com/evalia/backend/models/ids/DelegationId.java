package com.evalia.backend.models.ids;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class DelegationId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7106705746828090383L;

	@NotNull
	@Embedded
	@ManyToOne(optional = false)
	private GovernorateId governorateId;
	
	@NotBlank
	@Column(name = "delegation_name")
	private String name;
	
	
	public String toString() {
		return governorateId.toString() + "|" + name;
	}
	
}
