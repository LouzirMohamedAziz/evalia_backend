package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Account;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface AccountRepository extends JpaRepository<Account, String> {

	
    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

}
