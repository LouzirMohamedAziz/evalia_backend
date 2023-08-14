package com.evalia.backend.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.evalia.backend.models.Entity;
import com.evalia.backend.repositories.EntityRepository;
import com.evalia.backend.util.AccountUtils;

public class EntityUserDetailsServiceImpl implements UserDetailsService {

	private EntityRepository entityRepository;
	
	public EntityUserDetailsServiceImpl(EntityRepository entityRepository) {
		this.entityRepository = entityRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Entity actor = entityRepository.findByAccount_Login(login)
				.orElseThrow(() -> new UsernameNotFoundException("Login Not Found with login: " + login));

		return AccountUtils.buildFromAccount(actor.getAccount());
	}
}
