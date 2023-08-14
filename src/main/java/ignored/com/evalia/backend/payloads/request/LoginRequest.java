package ignored.com.evalia.backend.payloads.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
	
	@NotBlank
	private String login;

	@NotBlank
	private String password;
}
