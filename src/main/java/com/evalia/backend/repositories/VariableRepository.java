package com.evalia.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.evalia.backend.models.Variable;

public interface VariableRepository extends CrudRepository<Variable,String>{
    
}