package com.evalia.backend.models.ids;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Embeddable
public class GovernorateId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2772072415390521090L;

	@NotNull
	private String countryCode;
	
	@NotBlank
	@Column(name = "governorate_name")
	private String name;
	
	
	public String toString() {
		return countryCode + "|" + name;
	}
}
