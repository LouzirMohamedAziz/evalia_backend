package com.evalia.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Hamdi Jandoubi
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter

@Entity
public class SubSector {

	@EqualsAndHashCode.Include
	@Id
	private String name;
	
	@ManyToOne(optional = false)
	private Sector sector;
	
	@ManyToMany(mappedBy = "subSectors")
	private List<Indicator> indicators;
}
