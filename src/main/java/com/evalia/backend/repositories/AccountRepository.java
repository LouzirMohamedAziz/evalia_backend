package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Account;

/**
 * @author Hamdi Jandoubi
 *
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

}
