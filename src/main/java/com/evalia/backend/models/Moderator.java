package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="moderator")
public class Moderator{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moderatorId;


	public Moderator() {
	}

	public Moderator(Long moderatorId) {
		this.moderatorId = moderatorId;
	}

	public Long getModeratorId() {
		return this.moderatorId;
	}

	public void setModeratorId(Long moderatorId) {
		this.moderatorId = moderatorId;
	}

	public Moderator moderatorId(Long moderatorId) {
		setModeratorId(moderatorId);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Moderator)) {
			return false;
		}
		Moderator moderator = (Moderator) o;
		return Objects.equals(moderatorId, moderator.moderatorId);
	}

	@Override
	public String toString() {
		return "{" +
			" moderatorId='" + getModeratorId() + "'" +
			"}";
	}
	
}
