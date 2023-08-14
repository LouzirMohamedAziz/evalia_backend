package ignored.com.evalia.backend.payloads.response;

import java.util.List;

import com.evalia.backend.models.ERole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor()
@Getter
@Setter
public class JwtResponse {

	private Long id;

	private String username;

	private String email;

	private String token;

	private List<ERole> roles;

}