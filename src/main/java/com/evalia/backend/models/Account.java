package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Account implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(unique = true)
	private String username;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled = true;
	
	@Column(nullable = false)
	private boolean accountNonExpired = true;
	
	@Column(nullable = false)
	private boolean accountNonLocked = true;
	
	@Column(nullable = false)
	private boolean credentialsNonExpired = true;
	
	@Column(nullable = false)
	private boolean verified;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(optional = false)
	private Actor actor;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(fetch = FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<>();

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
