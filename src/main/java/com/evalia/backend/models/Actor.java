package com.evalia.backend.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.evalia.backend.metadata.ActorType;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actor_type")
public abstract class Actor {

	@EqualsAndHashCode.Include
	@Id
	private String identifier;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private ActorType actoryType;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private Account account;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	public void setAccount(Account account) {
		if(Objects.nonNull(this.account)) {
			this.account.setActor(null);
		}
		account.setActor(this);
		this.account = account;
	}
}
