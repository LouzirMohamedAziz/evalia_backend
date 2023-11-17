package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evalia.backend.models.Administrator;

@Repository 
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
