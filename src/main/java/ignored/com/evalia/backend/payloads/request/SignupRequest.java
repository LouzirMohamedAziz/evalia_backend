package ignored.com.evalia.backend.payloads.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.evalia.backend.models.Address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {

	@NotBlank
	private ActorType type;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String name;
	
	@NotBlank
	@Size(max = 90)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 3)
	private String isoCode;
	
	@NotBlank
	@Size(max = 15)
	@Email
	private String phone;
	
	@NotBlank
	private Address address;
	
	@NotBlank
	@Size(min = 3, max = 90)
	private String login;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	@Size(min = 3, max = 50)
	private String surname;
	
	private Date birthDate;
	
	private Address altAddress;
	
	private String taxIdentificationNumber;

}
