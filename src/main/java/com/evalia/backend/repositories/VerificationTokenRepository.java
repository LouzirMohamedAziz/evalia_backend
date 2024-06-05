package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.TokenType;
import com.evalia.backend.models.VerificationToken;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken,String>{
    
    VerificationToken findByTokenAndTokenType(String token, TokenType tokenType);

    VerificationToken findByAccount_Email(String email);

}
