package com.evalia.backend.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

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
public class BusinessGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@OneToOne(optional = false)
	private Professional owner;
	
	@OneToMany(mappedBy = "businessGroup")
	private List<Professional> members;
}
