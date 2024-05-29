package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Account;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface AccountRepository extends CrudRepository<Account, String> {

    Optional<Account> findByUsername(String username);

    Account findByEmail(String email);

    Optional<Account> findByPasswordResetToken(String token);

}
