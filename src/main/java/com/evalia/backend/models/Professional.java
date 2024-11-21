package com.evalia.backend.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter

@Entity
@DiscriminatorValue("professional")
public class Professional extends Ratable {

	@NotBlank
	private String name;
	
	@NotBlank
	private String phone;

	@OneToOne(cascade = CascadeType.ALL)
	private BusinessGroup ownedGroup;
	
	@ManyToOne
	private BusinessGroup businessGroup;
}
