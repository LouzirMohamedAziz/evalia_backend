package com.evalia.backend.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Account implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8211140625434787683L;

	@Id
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false)
	private String password;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private boolean emailVerified = false;
	
	@Column(nullable = false)
	private boolean enabled = true;

	@Column(nullable = false)
	private boolean accountNonExpired = true;

	@Column(nullable = false)
	private boolean accountNonLocked = true;

	@Column(nullable = false)
	private boolean credentialsNonExpired = true;

	@Column(nullable = false)
	private boolean verified = false;

	@EqualsAndHashCode.Exclude
	@OneToOne(optional = false)
	private Actor actor;

	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Authority> authorities = new ArrayList<>();

	public void setAuthorities(List<Authority> authorities) {
		if (Objects.isNull(authorities))
			return;
		this.authorities.clear();
		this.authorities.forEach(this.authorities::add);
	}
}
