package com.evalia.backend.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.evalia.backend.models.Administrative;
import com.evalia.backend.repositories.AdministrativeRepository;
import com.evalia.backend.util.AccountUtils;

public class AdministrativeUserDetailsServiceImpl implements UserDetailsService {

	private AdministrativeRepository administrativeRepository;
	
	public AdministrativeUserDetailsServiceImpl(AdministrativeRepository administrativeRepository) {
		this.administrativeRepository = administrativeRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Administrative actor = administrativeRepository.findByAccount_Login(login)
				.orElseThrow(() -> new UsernameNotFoundException("Login Not Found with login: " + login));
		
		return AccountUtils.buildFromAccount(actor.getAccount());
	}

}
