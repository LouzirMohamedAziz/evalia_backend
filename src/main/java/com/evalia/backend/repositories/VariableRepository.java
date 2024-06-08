package com.evalia.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalia.backend.models.Variable;

public interface VariableRepository extends JpaRepository<Variable,String>{
    
}