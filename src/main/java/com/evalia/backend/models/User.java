package com.evalia.backend.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String name;
	
	private String email;
	
	private String phone;


	public User() {
	}

	public User(Long userId, String name, String email, String phone) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User userId(Long userId) {
		setUserId(userId);
		return this;
	}

	public User name(String name) {
		setName(name);
		return this;
	}

	public User email(String email) {
		setEmail(email);
		return this;
	}

	public User phone(String phone) {
		setPhone(phone);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(userId, user.userId) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, name, email, phone);
	}

	@Override
	public String toString() {
		return "{" +
			" userId='" + getUserId() + "'" +
			", name='" + getName() + "'" +
			", email='" + getEmail() + "'" +
			", phone='" + getPhone() + "'" +
			"}";
	}
	
	
}