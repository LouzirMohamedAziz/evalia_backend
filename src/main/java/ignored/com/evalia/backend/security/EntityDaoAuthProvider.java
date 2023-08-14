package ignored.com.evalia.backend.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class EntityDaoAuthProvider extends DaoAuthenticationProvider{
	
	public EntityDaoAuthProvider(UserDetailsService service, PasswordEncoder encoder) {
		setUserDetailsService(service);
		setPasswordEncoder(encoder);
	}
}
