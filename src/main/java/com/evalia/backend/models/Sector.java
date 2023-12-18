package com.evalia.backend.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Sector {
	
	@Id
	private String name;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
	private List<SubSector> subSectors;
	
}
