package com.evalia.backend.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@DiscriminatorValue("moderator")
@Entity
public class Moderator extends com.evalia.backend.models.Entity{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moderatorId;
	
	@Column(unique = true, nullable = false)
	@NonNull
	@Setter
    @OneToOne
	private Account account;
	
}
