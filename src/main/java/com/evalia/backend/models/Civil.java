package com.evalia.backend.models;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
@DiscriminatorValue("civil")
public class Civil extends Actor {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;

	@NonNull
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@NotBlank
	private String phone;

}
