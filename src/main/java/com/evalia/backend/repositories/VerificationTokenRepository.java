package com.evalia.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.evalia.backend.models.TokenType;
import com.evalia.backend.models.VerificationToken;

@RepositoryRestResource(exported = false)
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, String>{
    
	
    Optional<VerificationToken> findByTokenAndTokenType(String token, TokenType tokenType);

    Optional<VerificationToken> findByAccount_Email(String email);
    
    Long deleteByAccount_Email(String email);

}
