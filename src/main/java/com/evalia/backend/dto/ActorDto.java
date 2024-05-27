package com.evalia.backend.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ActorDto {

	@NotEmpty
	private String identifier;
	
	@NotEmpty
	private String first_name;
	
	@NotEmpty
	private String last_name;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private Date birthDate;
	
	@Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$", flags = Flag.UNICODE_CASE)
	private String phone;
	
	@Email(message = "Email is not valid",
			regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty
	private String mail;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
}