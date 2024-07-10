package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.Account;

/**
 * @author Hamdi Jandoubi
 *
 */
@RepositoryRestResource(exported = false)
public interface AccountRepository extends JpaRepository<Account, String> {

	
    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);
    
    boolean existsByEmail(String email);

}
