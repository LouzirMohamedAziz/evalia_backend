package com.evalia.backend.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evalia.backend.models.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join Account a\s
      on t.account.username = a.username\s
      where a.username = :username and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(String username);

  Optional<Token> findByToken(String token);
}
