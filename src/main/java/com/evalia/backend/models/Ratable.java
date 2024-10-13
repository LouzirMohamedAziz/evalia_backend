package com.evalia.backend.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
public abstract class Ratable extends Actor{
	
	@ManyToOne
	private SubSector subSector;
	
	@JsonIgnore
	@OneToMany(mappedBy = "evaluatee", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Rating> ratings;

	public void setRatings(List<Rating> ratings) {
		if(Objects.isNull(ratings))
			return;
		this.ratings.clear();
		ratings.forEach(this::rate);
	}
	
	public void rate(Rating rating) {
		rating.setEvaluatee(this);
		this.ratings.add(rating);
	}
}
