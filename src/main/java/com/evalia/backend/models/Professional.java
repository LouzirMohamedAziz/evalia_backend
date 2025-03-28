package com.evalia.backend.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Professional extends Actor {

	@NotBlank
	private String name;
	
	@NotBlank
	private String phone;

	@OneToOne(cascade = CascadeType.ALL)
	private BusinessGroup ownedGroup;
	
	@ManyToOne
	private BusinessGroup businessGroup;

	@JsonIgnore
	@OneToMany(mappedBy = "evaluatee", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Rating> ratings;
	
	@ManyToOne
	private SubSector subSector;

	public void addRating(Rating rating) {
		rating.setEvaluatee(this);
		ratings.add(rating);
	}

	public void setRating(List<Rating> ratings) {
		if(Objects.isNull(ratings))
			return;
		this.ratings.clear();
		ratings.forEach(this::addRating);
	}   
}
