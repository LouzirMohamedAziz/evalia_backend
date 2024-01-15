package com.evalia.backend.models;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

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
	
	
	private String mail;

	@OneToOne(cascade = CascadeType.ALL)
	private BusinessGroup ownedGroup;
	
	@ManyToOne
	private BusinessGroup businessGroup;

	@OneToMany(mappedBy = "professional", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Rating> ratings;

	public void addRating(Rating rating) {
		rating.setProfessional(this);
		ratings.add(rating);
	}

	public void setRating(List<Rating> ratings) {
		if(Objects.isNull(ratings))
			return;
		this.ratings.clear();
		ratings.forEach(this::addRating);
	}
}
