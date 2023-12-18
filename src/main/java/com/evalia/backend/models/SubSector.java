package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hamdi Jandoubi
 *
 */
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter

@Entity
public class SubSector {

	@Id
	private String subSector;
	
	@EqualsAndHashCode.Exclude
	@ManyToOne(optional = false)
	private Sector sector;
	
}
