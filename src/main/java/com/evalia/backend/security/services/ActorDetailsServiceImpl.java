package com.evalia.backend.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.evalia.backend.models.Actor;
import com.evalia.backend.repositories.ActorRepository;


public class ActorDetailsServiceImpl implements UserDetailsService {

	private ActorRepository actorRepository;
	
	
	public ActorDetailsServiceImpl(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Actor actor = actorRepository.findByAccount_Username(username)
				.orElseThrow(() -> 
					new UsernameNotFoundException("Username : " + username + " not found"));

		return actor.getAccount();
	}
}