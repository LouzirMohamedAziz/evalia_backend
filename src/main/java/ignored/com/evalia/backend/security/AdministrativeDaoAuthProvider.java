package ignored.com.evalia.backend.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AdministrativeDaoAuthProvider extends DaoAuthenticationProvider{
	
	public AdministrativeDaoAuthProvider(UserDetailsService service, PasswordEncoder encoder) {
		setUserDetailsService(service);
		setPasswordEncoder(encoder);
	}
}
