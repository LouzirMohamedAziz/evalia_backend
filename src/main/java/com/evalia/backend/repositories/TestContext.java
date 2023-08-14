package com.evalia.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import ignored.com.evalia.backend.payloads.request.LoginRequest;

@Configuration
public class TestContext {

	@Autowired
    private ApplicationContext applicationContext;
	
	@Bean
	public LoginRequest loginRequest() {
		Boolean b = applicationContext.containsBeanDefinition("administrativeRepository");
		System.out.println("Context has admin repo ? => " + b);
		applicationContext.getBeansOfType(CrudRepository.class)
		.forEach((k, v) -> 
		System.out.printf("Name of Bean: %s and type of bean %s\n", k, v.getClass().getName()));
		return new LoginRequest();
	}
	
}
