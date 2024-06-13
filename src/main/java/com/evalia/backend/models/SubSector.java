package com.evalia.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.querydsl.core.annotations.QueryInit;

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
	
	@JsonBackReference
	@ManyToOne(optional = false)
	private Sector sector;
}
