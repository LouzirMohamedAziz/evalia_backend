package com.evalia.backend.utils.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.evalia.backend.repositories.AccountRepository;

public class AccountDetailsService implements UserDetailsService {

	private AccountRepository accountRepository;

	public AccountDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username : " + username + " not found"));
	}
}