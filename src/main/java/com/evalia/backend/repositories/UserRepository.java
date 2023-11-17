package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {

}
